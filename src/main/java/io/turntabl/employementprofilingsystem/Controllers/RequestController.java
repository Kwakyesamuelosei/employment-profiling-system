package io.turntabl.employementprofilingsystem.Controllers;

import io.fusionauth.jwt.JWTException;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import io.opentracing.Span;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.Models.RequestTO;
import io.turntabl.employementprofilingsystem.SendingMail.HolidayRequestMail;
import io.turntabl.employementprofilingsystem.Transfers.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.opentracing.Span;
import io.opentracing.Tracer;

@Api
@RestController
public class RequestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Tracer tracer;

    @CrossOrigin
    @ApiOperation("Make a holiday request")
    @PostMapping("/api/v1/request")
    public void makeARequest(@RequestBody RequestTO request) {
        Span span = tracer.buildSpan("Make New Holiday Request").start();
        span.setTag("http_method", "POST");
        span.setTag("http_url", "/api/v1/request");

        jdbcTemplate.update("insert into requests(requester_id, request_start_date, request_report_date) values(?,?,?)",
                request.getRequester_id(), request.getRequest_start_date(), request.getRequest_report_date());
        span.setTag("http_instance", "employee");
        span.setTag("http_statement", "insert into requests(requester_id, request_start_date, request_report_date) values(?,?,?)");

        SimpleDateFormat DateFor = new SimpleDateFormat("E, dd MMMM yyyy");
        String startDate = DateFor.format(request.getRequest_start_date());
        String reportDate = DateFor.format(request.getRequest_report_date());
        try {
            HolidayRequestMail.requestMessage(System.getenv("APPROVERS_MAIL"), request.getFrom() ,"Holiday request", startDate, reportDate, request.getRequester_name());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        span.finish();
    }

    @CrossOrigin
    @ApiOperation("Get all holiday requests for requester")
    @GetMapping("/api/v1/request/requester/{id}")

    public List<RequestTO> getRequestByRequesterId(@PathVariable("id") Integer id) {
        Span rootSpan = tracer.buildSpan("Get Holiday Request by ID").start();
        rootSpan.setTag("http_method", "GET");
        rootSpan.setTag("http_url", "/api/v1/request/requester/" + id);
        rootSpan.setTag("holiday_request_id", id);
        String REQUEST_QUERY = "select request_start_date, request_report_date, request_status.req_status from requests inner join request_status on requests.request_status_id = request_status.request_status_id where requester_id = ";
        List<RequestTO> query = this.jdbcTemplate.query(
                REQUEST_QUERY + "?",

                new Object[]{id},
                new BeanPropertyRowMapper<>(RequestTO.class)
        );
        rootSpan.log("retrieved all request data successfully");
        rootSpan.setTag("db.instance", "holiday request");
        rootSpan.setTag("db.statement", REQUEST_QUERY + id);
        rootSpan.finish();
        return query;
    }

    @CrossOrigin
    @ApiOperation("Get all holiday requests")
    @GetMapping("/api/v1/requests")

    public List<RequestTO> getAllRequests() {
        Span rootSpan = tracer.buildSpan("Get all holiday requests").start();
        String HOLIDAY_REQUEST_QUERY = "select request_start_date, request_report_date, request_status.req_status from requests inner join request_status on requests.request_status_id = request_status.request_status_id";
        List<RequestTO> query = this.jdbcTemplate.query(HOLIDAY_REQUEST_QUERY,
                new BeanPropertyRowMapper<RequestTO>(RequestTO.class)
        );

        rootSpan.setTag("http_method", "GET");
        rootSpan.setTag("http_url", "/api/v1/requests");
        rootSpan.setTag("db.instance", "holiday request");
        rootSpan.setTag("db.statement", HOLIDAY_REQUEST_QUERY);
        rootSpan.log("all holiday request data retrieved successfully");
        rootSpan.finish();
        return query;
    }

    @CrossOrigin
    @ApiOperation("Accept holiday request")
    @PutMapping("/api/v1/requests/approve/{id}")
    public void approveRequest(@PathVariable("id") Integer request_id) {
        Span rootSpan = tracer.buildSpan("Accept holiday request").start();
        String EMP_UPDATE_QUERY = "update requests set request_status_id = 3 where request_status_id = 1 and request_id = ";
        rootSpan.setTag("http_method", "PUT");
        rootSpan.setTag("request_id", request_id);
        rootSpan.setTag("request_url", "/api/v1/requests/approve/" + request_id);
        rootSpan.setTag("db_instance", "holiday request");
        this.jdbcTemplate.update(EMP_UPDATE_QUERY + "?", request_id);
        rootSpan.setTag("db_statement", EMP_UPDATE_QUERY + request_id);
        rootSpan.finish();
    }

    @CrossOrigin
    @ApiOperation("Decline holiday request")
    @PutMapping("/api/v1/requests/decline/{id}")
    public void declineRequest(@PathVariable("id") Integer request_id) {
        Span rootSpan = tracer.buildSpan("Decline holiday request").start();
        String EMPLOYEE_UPDATE_QUERY = "update requests set request_status_id = 2 where request_status_id = 1 and request_id = ";
        rootSpan.setTag("http_method", "PUT");
        rootSpan.setTag("request_id", request_id);
        rootSpan.setTag("request_url", "/api/v1/requests/approve/" + request_id);
        this.jdbcTemplate.update(EMPLOYEE_UPDATE_QUERY + "?", request_id);
        rootSpan.setTag("db_statement",EMPLOYEE_UPDATE_QUERY + request_id);
        rootSpan.finish();
    }



    @CrossOrigin
    @ApiOperation("validating employee with OIDC")
    @PostMapping("/api/v1/request/requester/validate")
    public Map<String, Object> checkToken(@RequestHeader("access-token") String token){
        Span rootSpan = tracer.buildSpan("validating employee with OIDC").start();
        rootSpan.setTag("http_method", "POST");
        rootSpan.setTag("request_url", "/api/v1/request/requester/validate");
        rootSpan.setTag("access_token", token);
        Map<String, Object> response  = new HashMap<>();

        Verifier verifier = RSAVerifier.newVerifier(Paths.get("public_key.pem"));
        try {
            // Verify and decode the encoded string JWT to a rich object
            JWT jwt = JWT.getDecoder().decode(token,verifier);
            response.put("success", true);
            response.put("decoded_token", jwt);
            rootSpan.log(jwt.toString());
            rootSpan.setTag("validation_response", response.toString());
            rootSpan.finish();
            return response;
        } catch (JWTException e) {
            e.printStackTrace();
            response.put("success", false);
            rootSpan.finish();
            return response;
        }
    }

    @CrossOrigin
    @ApiOperation("Checking available email")
    @GetMapping("api/v1/requester/verifymail/{email}")
    public Map<String, Object> check_employee_exits(@PathVariable("email") String email) {
        Span rootSpan = tracer.buildSpan("Checking available email").start();
        rootSpan.setTag("http_method", "GET");
        rootSpan.setTag("http_url", "api/v1/requester/verifymail/"+ email);
        Map<String, Object> response = new HashMap<>();
        String EMPLOYEE_QUERY = "select * from employee where employee_email = ";
        response.put("response", this.jdbcTemplate.query(EMPLOYEE_QUERY + "?",
                new Object[]{email},
                BeanPropertyRowMapper.newInstance(Employee.class)
        ) );
        rootSpan.setTag("db_statement", EMPLOYEE_QUERY + email);
        rootSpan.log("available email checked successfully");
        rootSpan.finish();
        return response;
    }

    @CrossOrigin
    @ApiOperation("Add unexisting employee")
    @PostMapping("api/v1/requester/addemployee")
    public Map<String, Object> addemployeeDetails(@RequestBody Employee employeeDetails) {
        Span span = tracer.buildSpan("Add unexisting employee").start();
        span.setTag("http_method", "POST");
        span.setTag("http_url", "api/v1/requester/addemployee");
        span.setTag("request_body", employeeDetails.toString());

        Map<String, Object> response = new HashMap<>();

        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate).withTableName("employee").usingGeneratedKeyColumns("employee_id");
            Map<String, Object> insertEmployeeDetails = new HashMap<>();
        insertEmployeeDetails.put("employee_firstname", employeeDetails.getEmployee_firstname());
        insertEmployeeDetails.put("employee_lastname", employeeDetails.getEmployee_lastname());
        insertEmployeeDetails.put("employee_email", employeeDetails.getEmployee_email());

        Number Key = insertActor.executeAndReturnKey(insertEmployeeDetails);
        if (Key != null){
            response.put("success",true);
            response.put("employee_id",Key.longValue());
            span.log("Failed to add new employee");
            span.log("Failed to add new employee");
        }else {
            response.put("success",false);
            response.put("msg","Failed to add new employee, try again later");
            span.log("Failed to add new employee, try again later");
        }
        span.finish();
        return  response;
    }
}


