package com.example.ngodonations.repository;

import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    Employee findEmployeeByUsername(String username);
    List<Employee> findAllByName(String name);

}
