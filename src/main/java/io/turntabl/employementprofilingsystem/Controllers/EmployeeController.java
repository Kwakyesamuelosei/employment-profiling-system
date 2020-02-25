package io.turntabl.employementprofilingsystem.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.employementprofilingsystem.DAO.EmployeeDAO;
import io.turntabl.employementprofilingsystem.Models.AddEmployee;
import io.turntabl.employementprofilingsystem.Models.EditEmployee;
import io.turntabl.employementprofilingsystem.Transfers.Employee;
import io.turntabl.employementprofilingsystem.Transfers.UpdateEmployee;
import io.turntabl.employementprofilingsystem.Utilities.Date;
import io.turntabl.employementprofilingsystem.Utilities.Parsor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api
@RestController
class EmployeeController implements EmployeeDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Parsor parsor = new Parsor();
    Date date = new Date();

    @ApiOperation("Add New Employee")
    @CrossOrigin(origins = "*")
    @PostMapping("/v1/api/employee")
    @Override
    public Map<String, Object> addEmployee(@RequestBody AddEmployee requestData) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> request = new HashMap<>();
        request.put("employee_firstname",requestData.getEmployee_firstname());
        request.put("employee_lastname",requestData.getEmployee_lastname());
        request.put("employee_phonenumber",requestData.getEmployee_phonenumber());
        request.put("employee_email",requestData.getEmployee_email());
        request.put("employee_role",requestData.getEmployee_role());
        request.put("employee_address",requestData.getEmployee_address());
        request.put("employee_dev_level",requestData.getEmployee_dev_level());
        request.put("employee_gender",requestData.getEmployee_gender());

        try{

            List<String> requiredParams = Arrays.asList(
                    "employee_email",
                    "employee_role"
            );
            Map<String, Object> result = parsor.validate_params(request,requiredParams);
            if (result.get("code").equals("00")){

                if (this.checkExistingEmployee(requestData.getEmployee_email())){
                    response.put("code","01");
                    response.put("msg","Employee already exist with the same email");
                }else {
                    java.sql.Date employee_hire_date = date.getCurrentDate();
                    Boolean employee_onleave = false;
                    String employee_dev_level = requestData.getEmployee_dev_level();
                    String employee_gender = requestData.getEmployee_gender();
                    String employee_role = requestData.getEmployee_role();
                    String employee_status = "Active";

                    SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate).withTableName("employee").usingGeneratedKeyColumns("employee_id");

                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("employee_firstname",requestData.getEmployee_firstname());
                    parameters.put("employee_lastname",requestData.getEmployee_lastname());
                    parameters.put("employee_phonenumber",requestData.getEmployee_phonenumber());
                    parameters.put("employee_email",requestData.getEmployee_email());
                    parameters.put("employee_address",requestData.getEmployee_address());
                    parameters.put("employee_role",employee_role.toUpperCase());
                    parameters.put("employee_status",employee_status.toUpperCase());
                    parameters.put("employee_dev_level",employee_dev_level.toUpperCase());
                    parameters.put("employee_hire_date",employee_hire_date);
                    parameters.put("employee_onleave",employee_onleave);
                    parameters.put("employee_gender",employee_gender.toUpperCase());

                    Number key = insertActor.executeAndReturnKey(parameters);
                    if (key != null){
                        response.put("code","00");
                        response.put("msg","New employee added successfully");
                        response.put("data",key.longValue());
                    }else {
                        response.put("code","01");
                        response.put("msg","Failed to add new employee, try again later");
                    }
                }
            }else {
                response.put("code",result.get("code"));
                response.put("msg",result.get("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;
    }
    @ApiOperation("List of Employee")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/employees")
    @Override
    public Map<String, Object> getAllEmployee(){

        Map<String, Object> response = new HashMap<>();
        try{
            List<Employee> employeeTOList =  jdbcTemplate.query(
                    "select * from employee",
                    BeanPropertyRowMapper.newInstance(Employee.class)
            );
            response.put("code","00");
            response.put("msg","Data retrieved successfully");
            response.put("data",employeeTOList);

        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;
    }

    @ApiOperation("Get Employee By Id")
    @CrossOrigin(origins = "*")
    @GetMapping("/v1/api/employee/{id}")
    @Override
    public Map<String, Object> getEmployeeById(@PathVariable("id") Integer id){
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> request = new HashMap<>();
        request.put("id",id);

        try{
            List<String> requiredParams = Arrays.asList(
                    "id"
            );
            Map<String, Object> valid = parsor.validate_params(request,requiredParams);
            if (valid.get("code").equals("00")){

                List<Employee> employee = jdbcTemplate.query(
                        "select * from employee where employee_id = ?",
                        new Object[]{id},
                        BeanPropertyRowMapper.newInstance(Employee.class)
                );

                if (!employee.isEmpty()){
                    response.put("code","00");
                    response.put("msg","Data retrieved successfully");
                    response.put("data",employee.get(0));
                }else {
                    response.put("code","00");
                    response.put("msg","No Data found");
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

    @ApiOperation("Edit Employee Profile")
    @CrossOrigin(origins = "*")
    @PutMapping("/v1/api/employee")
    @Override
    public Map<String, Object> updateEmployeeProfile(@RequestBody EditEmployee editEmployee){

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> request = new HashMap<>();
        request.put("employee_id",editEmployee.getEmployee_id());
        request.put("employee_firstname",editEmployee.getEmployee_firstname());
        request.put("employee_lastname",editEmployee.getEmployee_lastname());
        request.put("employee_role",editEmployee.getEmployee_role());
        request.put("employee_address",editEmployee.getEmployee_address());
        request.put("employee_dev_level",editEmployee.getEmployee_dev_level());
        request.put("employee_status",editEmployee.getEmployee_status());

        try{
            List<String> requiredParams = Arrays.asList(
                    "employee_id"
            );
            Map<String, Object> valid = parsor.validate_params(request,requiredParams);
            if (valid.get("code").equals("00")){
                Map<String, Object> updated_params = this.check_updated_params(editEmployee);
                if (updated_params.get("code").equals("00")){
                    UpdateEmployee updateEmployee = (UpdateEmployee) updated_params.get("data");
                    int resp = jdbcTemplate.update(
                            "update employee set employee_firstname = ?, employee_lastname = ?, employee_address = ?,employee_dev_level = ?,employee_status = ?,employee_role = ?  where employee_id = ?",
                            new Object[]{
                                    updateEmployee.getEmployee_firstname(),
                                    updateEmployee.getEmployee_lastname(),
                                    updateEmployee.getEmployee_address(),
                                    updateEmployee.getEmployee_dev_level().toUpperCase(),
                                    updateEmployee.getEmployee_status().toUpperCase(),
                                    updateEmployee.getEmployee_role().toUpperCase(),
                                    editEmployee.getEmployee_id()
                            }
                    );
                    if (resp > 0){
                        response.put("code","00");
                        response.put("msg","Employee updated successfully");
                    }else {
                        response.put("code","01");
                        response.put("msg","Failed to update employee details");
                    }
                }else {
                    response.put("code",updated_params.get("code"));
                    response.put("msg",updated_params.get("msg"));
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

    private Map<String, Object> check_updated_params(EditEmployee editEmployee){
        Map<String, Object> response = new HashMap<>();
        UpdateEmployee updateEmployee = new UpdateEmployee();
        try{
            Integer employee_id = editEmployee.getEmployee_id();
            List<Employee> employee = jdbcTemplate.query(
                    "select * from employee where employee_id = ?",
                    new Object[]{employee_id},
                    BeanPropertyRowMapper.newInstance(Employee.class)
            );
            if (!employee.isEmpty()){
                Employee employeeData = employee.get(0);
                if(editEmployee.getEmployee_firstname().isEmpty()){
                    updateEmployee.setEmployee_firstname(employeeData.getEmployee_firstname());
                }else {
                    updateEmployee.setEmployee_firstname(editEmployee.getEmployee_firstname());
                }
                if(editEmployee.getEmployee_lastname().isEmpty()){
                    updateEmployee.setEmployee_lastname(employeeData.getEmployee_lastname());
                }else {
                    updateEmployee.setEmployee_lastname(editEmployee.getEmployee_lastname());
                }
                if(editEmployee.getEmployee_address().isEmpty()){
                    updateEmployee.setEmployee_address(employeeData.getEmployee_address());
                }else {
                    updateEmployee.setEmployee_address(editEmployee.getEmployee_address());
                }
                if(editEmployee.getEmployee_dev_level().isEmpty()){
                    updateEmployee.setEmployee_dev_level(employeeData.getEmployee_dev_level());
                }else {
                    updateEmployee.setEmployee_dev_level(editEmployee.getEmployee_dev_level());
                }
                if(editEmployee.getEmployee_status().isEmpty()){
                    updateEmployee.setEmployee_status(employeeData.getEmployee_status());
                }else {
                    updateEmployee.setEmployee_status(editEmployee.getEmployee_status());
                }
                if(editEmployee.getEmployee_role().isEmpty()){
                    updateEmployee.setEmployee_role(employeeData.getEmployee_role());
                }else {
                    updateEmployee.setEmployee_role(editEmployee.getEmployee_role());
                }

                response.put("code","00");
                response.put("msg","Data retrieved successfully");
                response.put("data",updateEmployee);

            }else {
                response.put("code","01");
                response.put("msg","Employee with this ID ["+employee_id+"] doesn't exist");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Something went wrong, try again later");
        }
        return response;
    }

    private Boolean checkExistingEmployee(String email){
        Boolean response = null;

        try{
            List<Employee> employee = jdbcTemplate.query(
                    "select * from employee where employee_email = ?",
                    new Object[]{email},
                    BeanPropertyRowMapper.newInstance(Employee.class)
            );
            if (employee.isEmpty()){
                response = false;
            }else {
                response = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
