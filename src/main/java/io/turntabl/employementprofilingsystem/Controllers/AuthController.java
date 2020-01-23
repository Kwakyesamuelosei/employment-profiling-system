package io.turntabl.employementprofilingsystem.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.Transfers.*;
import io.turntabl.employementprofilingsystem.Utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@Api
@RestController
public class AuthController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Parsor parsor = new Parsor();

    @ApiOperation("Get Employee By Email")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/login/{employee_email}")
    public Map<String,Object> getEmployeeByEmail(@PathVariable("employee_email") String email) {

        Map<String, Object> request = new HashMap<>();
        Map<String, Object> response = new HashMap<>();

        try{
            request.put("employee_email", email);

            List<String> requiredParams = Arrays.asList(
                    "employee_email"
            );
            Map<String, Object> valid = parsor.validate_params(request, requiredParams);
            if (valid.get("code").equals("00")) {
                List<Employee>  employee = jdbcTemplate.query(
                        "select * from employee where employee_email = ?",
                        new Object[]{email},
                        BeanPropertyRowMapper.newInstance(Employee.class)
                );
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
        return response;
    }

}