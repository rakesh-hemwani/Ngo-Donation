package com.example.ngodonations.controller;

import com.example.ngodonations.exceptions.DuplicateDonorException;
import com.example.ngodonations.exceptions.DuplicateEmployeeException;
import com.example.ngodonations.exceptions.NoSuchEmployeeException;
import com.example.ngodonations.model.Admin;
import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.Employee;
import com.example.ngodonations.service.AdminService;
import com.example.ngodonations.service.AdminServiceImpl;
import com.example.ngodonations.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/addAdmin")
    public String addAmin(@RequestBody Admin admin){
        Boolean flag=adminService.addAdmin(admin);
        if(!flag){
            return "Sorry...";
        }
        return "admin Addedd..";
    }

    @PostMapping("/addEmployee/{id}")
    public String addEmployee(@PathVariable Long id,@RequestBody Employee employee) throws DuplicateEmployeeException {
        if(employeeService.getEmployeeByUsername(employee.getUsername())!=null){
            throw new DuplicateEmployeeException("Username Already There");
        }
        adminService.addEmployee(id,employee);
        return "Employee Addedd..";
    }

    @ExceptionHandler(DuplicateEmployeeException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateEmployeeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @PostMapping("/modifyEmployee/{id}")
    public String modifyEmployee(@PathVariable("id") Long employeeId, @RequestBody Employee employee) throws NoSuchEmployeeException {

            Employee modifiedEmployee = adminService.findEmployeeById(employeeId);
            if(modifiedEmployee!=null) {
                modifiedEmployee.setUsername(employee.getUsername());
                modifiedEmployee.setPassword(employee.getPassword());
                adminService.modifyEmployee(modifiedEmployee);
                return "Employee modified successfully!";
            }
            else{
                throw new NoSuchEmployeeException("No Employee Found");
            }

    }
    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<?> handleDuplicateUserException(NoSuchEmployeeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @DeleteMapping("/removeEmployee/{id}")
    public String removeEmployee(@PathVariable("id") Long employeeId) throws NoSuchEmployeeException {
        Employee modifiedEmployee = adminService.findEmployeeById(employeeId);
        if(modifiedEmployee!=null) {
            adminService.removeEmployee(employeeId);
            return "Employee removed successfully!";
        }
        else {
            throw new NoSuchEmployeeException("No Employee Found");
        }
    }

    @GetMapping("/EmployeeById/{id}")
    public Employee findEmployeeById(@PathVariable("id") Long employeeId) throws NoSuchEmployeeException{
        Employee modifiedEmployee = adminService.findEmployeeById(employeeId);
        if(modifiedEmployee!=null) {
            return modifiedEmployee;
        }
        else {
            throw new NoSuchEmployeeException("No Employee Found");
        }
    }

    @GetMapping("/EmployeeByName/{name}")
    public List<Employee> findEmployeeByName(String name){
            return adminService.findEmployeeByName(name);
    }

    @GetMapping("/AllEmployee")
    public List<Employee> findAllEmployee() {
            return adminService.findAllEmployee();
    }

}


