package io.turntabl.employementprofilingsystem.Controllers;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.Transfers.*;
import io.turntabl.employementprofilingsystem.Utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api
@RestController
public class AuthController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    Tracer tracer;

    Parser parser = new Parser();

    @ApiOperation("Get Employee By HolidayRequestMail")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/login/{employee_email}")
    public Map<String,Object> getEmployeeByEmail(@PathVariable("employee_email") String email) {
        Span rootSpan = tracer.buildSpan("Get Employee By HolidayRequestMail").start();
        rootSpan.setTag("http_method", "GET");
        rootSpan.setTag("request_email", email);
        rootSpan.setTag("http_url", "/v1/api/login/" + email);

        String EMPLOYEE_QUERY = "select * from employee where employee_email = ";
        Map<String, Object> request = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

        try{
            request.put("employee_email", email);

            List<String> requiredParams = Arrays.asList(
                    "employee_email"
            );
            Map<String, Object> valid = parser.validate_params(request, requiredParams);
            if (valid.get("code").equals("00")) {
                List<Employee>  employee = jdbcTemplate.query(
                        EMPLOYEE_QUERY +" ?",
                        new Object[]{email},
                        BeanPropertyRowMapper.newInstance(Employee.class)
                );
                rootSpan.setTag("db.statement", EMPLOYEE_QUERY + email);
                if (!employee.isEmpty()){
                    response.put("code","00");
                    response.put("msg","Data retrieved successfully");
                    response.put("data",employee.get(0));
                }else{
                    response.put("code","00");
                    response.put("msg","No Data Found");
                    response.put("data",new HashMap<>());
                }
            }else {
                response.put("code",valid.get("code"));
                response.put("msg",valid.get("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        rootSpan.finish();
        return response;
    }
}