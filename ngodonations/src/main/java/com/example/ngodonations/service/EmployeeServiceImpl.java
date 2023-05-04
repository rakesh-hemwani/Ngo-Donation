package com.example.ngodonations.service;

import com.example.ngodonations.model.Employee;
import com.example.ngodonations.model.NeedyPeople;
import com.example.ngodonations.repository.EmployeeRepository;
import com.example.ngodonations.repository.NeedyPeopleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    NeedyPeopleRepository needyPeopleRepository;


    @Override
    public boolean login(String Username, String Password) {
        Employee employee=employeeRepository.findEmployeeByUsername(Username);
        if(employee!=null && employee.getPassword().equals(Password)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean addNeedyPerson(NeedyPeople person) {
        needyPeopleRepository.save(person);
        return true;
    }

    @Override
    public boolean removeNeedyPerson(NeedyPeople person) {
        needyPeopleRepository.deleteById(person.getNeedyPersonId());
        return true;
    }

    @Override
    public NeedyPeople findNeedyPeopleById(Long id) {
        if(needyPeopleRepository.findById(id).isPresent()){
            return needyPeopleRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public List<NeedyPeople> findNeedyPeopleByName(String name) {
        return needyPeopleRepository.getAllByNeedyPersonName(name);
    }

    @Override
    public List<NeedyPeople> findAllNeedyPeople() {
        return needyPeopleRepository.findAll();
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeRepository.findEmployeeByUsername(username);
    }

    public boolean addEmployee(Employee employee) {
        employeeRepository.save(employee);
        return true;
    }
}
