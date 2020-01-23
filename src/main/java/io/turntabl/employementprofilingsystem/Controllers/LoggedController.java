package io.turntabl.employementprofilingsystem.Controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.DAO.LoggedDAO;
import io.turntabl.employementprofilingsystem.Transfers.LoggedProjectTO;
import io.turntabl.employementprofilingsystem.Transfers.LoggedSickTO;
import io.turntabl.employementprofilingsystem.Transfers.LoggedTO;
import io.turntabl.employementprofilingsystem.Transfers.LoggedVacationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
public class LoggedController implements LoggedDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ApiOperation("Log Project Hour")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/v1/api/addloggedproject")
    @Override
    public Map<String, Object> addLoggedProject(@RequestBody LoggedProjectTO loggedProjectTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            this.jdbcTemplate.update("insert into LoggedProject (project_id, employee_id, project_hours, project_date) values (?, ?, ?, ?)",
                    loggedProjectTO.getProject_id(), loggedProjectTO.getEmployee_id(), loggedProjectTO.getProject_hours(), loggedProjectTO.getProject_date());
            response.put("code", "00");
            response.put("msg", "Project logged successfully");
        }catch(Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");

        }
        return response;
    }


    @ApiOperation(" get Logged Projects ")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/v1/api/getloggedproject")
    @Override
    public Map<String, Object>  getAllLoggedProject() {

        Map<String, Object> response = new HashMap<>();

        try {
            List<LoggedProjectTO> pro =jdbcTemplate.query(
                    "select * from LoggedProject", BeanPropertyRowMapper.newInstance(LoggedProjectTO.class));
            response.put("code","00");
            response.put("msg","Data retrieved successfully");
            response.put("data",pro);
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;

    }

    @ApiOperation(" get Logged Sick ")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/v1/api/getloggedsick")
    @Override
    public Map<String, Object>   getAllLoggedSick() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<LoggedSickTO> sick =jdbcTemplate.query(
                    "select * from LoggedSick", BeanPropertyRowMapper.newInstance(LoggedSickTO.class));
            response.put("code","00");
            response.put("msg","Data retrieved successfully");
            response.put("data",sick);
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;

    }


    @ApiOperation("Log Sick")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/v1/api/addloggedsick")
    @Override
    public Map<String, Object> addLoggedSick(@RequestBody LoggedSickTO loggedSickTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            this.jdbcTemplate.update("insert into LoggedSick (employee_id, sick_date) values (?, ?)",
                    loggedSickTO.getEmployee_id(), loggedSickTO.getSick_date());
            response.put("code", "00");
            response.put("msg", "Sick logged successfully");
        }catch(Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");

        }
        return response;

    }


    @ApiOperation(" get Logged Vacation ")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/v1/api/getloggedvacation")
    @Override
    public Map<String, Object> getAllLoggedVacation() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<LoggedVacationTO> vac = jdbcTemplate.query(
                    "select * from LoggedVacation", BeanPropertyRowMapper.newInstance(LoggedVacationTO.class));

            response.put("code","00");
            response.put("msg","Data retrieved successfully");
            response.put("data",vac);
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;

    }


    @ApiOperation("Log Vacation")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/v1/api/addloggedvaction")
    @Override
    public Map<String, Object> addLoggedVacation(@RequestBody LoggedVacationTO loggedVacationTO) {
        Map<String, Object> response = new HashMap<>();
        try{
            this.jdbcTemplate.update("insert into LoggedVacation (employee_id, vacation_date) values (?, ?)",
                    loggedVacationTO.getEmployee_id(), loggedVacationTO.getVacation_date());
            response.put("code", "00");
            response.put("msg", "Vacation logged successfully");
        }catch(Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");

        }
        return response;

    }

    @ApiOperation(" get ALL Logged Hour")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/v1/api/getlogged/{end_date}")
    @Override
    public Map<String, Object>  getAllLogged(@RequestParam("end_date") Date endDate) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<LoggedTO> log = jdbcTemplate.query("select * from log_func(?::date)",
                    new Object[]{endDate},
                    BeanPropertyRowMapper.newInstance(LoggedTO.class));

            response.put("code","00");
            response.put("msg","Data retrieved successfully");
            response.put("data",log);
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;
    }
}
