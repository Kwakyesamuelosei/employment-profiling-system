package io.turntabl.employementprofilingsystem.Controllers;

import io.fusionauth.jwt.JWTException;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.Models.RequestTO;
import io.turntabl.employementprofilingsystem.SendingMail.ApproverMail;
import io.turntabl.employementprofilingsystem.Transfers.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*")
@Api
@RestController
public class RequestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @CrossOrigin(origins = "*")
    @ApiOperation("Make a holiday request")
    @PostMapping("/api/v1/request")
    public void makeARequest(@RequestBody RequestTO request) {
        jdbcTemplate.update("insert into requests(requester_id, request_start_date, request_report_date) values(?,?,?)",
                request.getRequester_id(), request.getRequest_start_date(), request.getRequest_report_date());

        SimpleDateFormat DateFor = new SimpleDateFormat("E, dd MMMM yyyy");
        String startDate = DateFor.format(request.getRequest_start_date());
        String reportDate = DateFor.format(request.getRequest_report_date());

        try {
            ApproverMail.requestMessage(System.getenv("APPROVER_EMAIL"),request.getFrom(), "Holiday request", startDate, reportDate);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Get all holiday requests for requester")
    @GetMapping("/api/v1/request/requester/{id}")

    public List<RequestTO> getRequestByRequesterId(@PathVariable("id") Integer id) {
        return this.jdbcTemplate.query(
                " select request_start_date, request_report_date, request_status.req_status from requests inner join request_status on requests.request_status_id = request_status.request_status_id where requester_id =?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(RequestTO.class)
        );
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Get all holiday requests")
    @GetMapping("/api/v1/requests")

    public List<RequestTO> getAllRequests() {
        return this.jdbcTemplate.query("select request_id, employee_email as requester_email, request_start_date, request_report_date, request_status.req_status from requests inner join request_status on requests.request_status_id = request_status.request_status_id inner join employee on requests.requester_id =employee.employee_id order by req_status desc;",
                new BeanPropertyRowMapper<RequestTO>(RequestTO.class)
        );
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Accept holiday request")
    @PutMapping("/api/v1/requests/approve/{id}")
    public void approveRequest(@PathVariable("id") Integer request_id) throws IOException, GeneralSecurityException {
        this.jdbcTemplate.update("update requests set request_status_id = 3 where request_id = ?", request_id);


        List<RequestTO> user_details = this.jdbcTemplate.query(
                "select employee.employee_email as requester_email, requests.request_start_date, requests.request_report_date from employee inner join requests on employee.employee_id =requests.requester_id where requests.request_id =?",
                new Object[]{request_id},
                new BeanPropertyRowMapper<>(RequestTO.class));

        SimpleDateFormat DateFor = new SimpleDateFormat("E, dd MMMM yyyy");
        String startDate = DateFor.format(user_details.get(0).getRequest_start_date());
        String reportDate = DateFor.format(user_details.get(0).getRequest_report_date());


        ApproverMail.approveMessage(user_details.get(0).getRequester_email(), System.getenv("APPROVER_EMAIL"), "Holiday Request Response", startDate, reportDate);

    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Decline holiday request")
    @PutMapping("/api/v1/requests/decline/{id}")
    public void declineRequest(@PathVariable("id") Integer request_id) {
        this.jdbcTemplate.update("update requests set request_status_id = 2 where request_id = ?", request_id);

        List<RequestTO> user_details = this.jdbcTemplate.query(
                "select employee.employee_email as requester_email, requests.request_start_date, requests.request_report_date from employee inner join requests on employee.employee_id =requests.requester_id where requests.request_id =?",
                new Object[]{request_id},
                new BeanPropertyRowMapper<>(RequestTO.class));

        SimpleDateFormat DateFor = new SimpleDateFormat("E, dd MMMM yyyy");
        String startDate = DateFor.format(user_details.get(0).getRequest_start_date());
        String reportDate = DateFor.format(user_details.get(0).getRequest_report_date());


        try {
            ApproverMail.declinedMessage(user_details.get(0).getRequester_email(), System.getenv("APPROVER_EMAIL"), "Holiday request response", startDate, reportDate);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("validating employee with OIDC")
    @PostMapping("/api/v1/validate")
    public Map<String, Object> checkToken(@RequestHeader("access-token") String token) {
        Map<String, Object> response = new HashMap<>();
        public_key();
        Verifier verifier = RSAVerifier.newVerifier(Paths.get("public_key.pem"));

        try {
            JWT jwt = JWT.getDecoder().decode(token, verifier);
            response.put("success", true);
            response.put("decoded_token", jwt);
            return response;
        } catch (JWTException e) {
            e.getCause();
            response.put("success", false);
            return response;
        }
    }

    public static void public_key() {
        try {
            FileWriter pub_key = new FileWriter("public_key.pem");
            pub_key.write(System.getenv("PUBLIC_KEY"));
            pub_key.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @CrossOrigin(origins = "*")
    @ApiOperation("Checking available email")
    @GetMapping("/api/v1/verifymail/{email}")
    public Map<String, Object> check_employee_exits(@PathVariable("email") String email) {
        Map<String, Object> response = new HashMap<>();

        response.put("response", this.jdbcTemplate.query("select * from employee where employee_email = ?",
                new Object[]{email},
                BeanPropertyRowMapper.newInstance(Employee.class)
        ));
        return response;
    }

    @CrossOrigin(origins = "*")
    @ApiOperation("Checking available email")
    @PostMapping("api/v1/addemployee")
    public Map<String, Object> addemployeeDetails(@RequestBody Employee employeeDetails) {
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
        }else {
            response.put("success",false);
            response.put("msg","Failed to add new employee, try again later");
        }
        return  response;
    }
}