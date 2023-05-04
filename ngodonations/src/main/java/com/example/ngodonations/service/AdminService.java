package com.example.ngodonations.service;

import com.example.ngodonations.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    public boolean addEmployee(Long id,Employee employee) ;
    public Employee modifyEmployee(Employee employee) ;
    public boolean removeEmployee(Long employeeId);
    public Employee findEmployeeById(Long employeeId) ;
    public List<Employee> findEmployeeByName(String name);
    public List<Employee> findAllEmployee() ;
}
