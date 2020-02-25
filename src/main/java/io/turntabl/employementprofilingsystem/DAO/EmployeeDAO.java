package io.turntabl.employementprofilingsystem.DAO;

import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.Models.AddEmployee;
import io.turntabl.employementprofilingsystem.Models.EditEmployee;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface EmployeeDAO {
    @ApiOperation("Add New Employee")
    @CrossOrigin(origins = "*")
    @PostMapping("/v1/api/employee")
    Map<String, Object> addEmployee(@RequestBody AddEmployee requestData);

    @ApiOperation("List of Employee")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/employees")
    Map<String, Object> getAllEmployee();

    @ApiOperation("Get Employee By Id")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/employee/{id}")
    Map<String, Object> getEmployeeById(@PathVariable("id") Integer id);

    @ApiOperation("Edit Employee Profile")
    @CrossOrigin(origins = "*")
    @PutMapping("/v1/api/employee")
    Map<String, Object> updateEmployeeProfile(@RequestBody EditEmployee editEmployee);
}
