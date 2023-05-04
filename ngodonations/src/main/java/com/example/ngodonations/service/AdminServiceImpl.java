package com.example.ngodonations.service;

import com.example.ngodonations.exceptions.DuplicateEmployeeException;
import com.example.ngodonations.exceptions.NoSuchDonorException;
import com.example.ngodonations.exceptions.NoSuchEmployeeException;
import com.example.ngodonations.model.Admin;
import com.example.ngodonations.model.Donation;
import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.Employee;
import com.example.ngodonations.repository.AdminRepository;
import com.example.ngodonations.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public boolean addAdmin(Admin admin){
       Admin admin1= adminRepository.save(admin);
       if(admin1==null)return false;
        return true;
    }

    @Override
    public boolean addEmployee(Long id,Employee employee) {
        Optional<Admin> admin=adminRepository.findById(id);
        if(admin.isPresent()){
            Admin admin1=admin.get();
            employee.setAdmin(admin1);
            employeeRepository.save(employee);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Employee modifyEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employeeRepository.save(employee);
    }

    @Override
    public boolean removeEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return true;
    }

    @Override
    public Employee findEmployeeById(Long employeeId){
        if(employeeRepository.findById(employeeId).isPresent()){
            return employeeRepository.findById(employeeId).get();
        }
        return null;
    }

    @Override
    public List<Employee> findEmployeeByName(String name) {
        return employeeRepository.findAllByName(name);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return  employeeRepository.findAll();
    }


}
