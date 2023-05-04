package com.example.ngodonations.controller;


import com.example.ngodonations.exceptions.NoSuchEmployeeException;
import com.example.ngodonations.model.NeedyPeople;
import com.example.ngodonations.service.EmployeeService;
import com.example.ngodonations.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    //Login
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> loginData) {
        String name = loginData.get("username");
        String phone = loginData.get("password");
        if (employeeService.login(name, phone)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    @PostMapping("/addNeedyPerson")
    public ResponseEntity<String> addNeedyPerson(@RequestBody NeedyPeople needyPerson) {
        boolean isAdded = employeeService.addNeedyPerson(needyPerson);
        if (isAdded) {
            return new ResponseEntity<>("Needy person added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add needy person.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/removeNeedyPerson/{id}")
    public ResponseEntity<String> removeNeedyPerson(@PathVariable Long id) {
        NeedyPeople needyPerson = employeeService.findNeedyPeopleById(id);
        if (needyPerson != null) {
            boolean isRemoved = employeeService.removeNeedyPerson(needyPerson);
            if (isRemoved) {
                return new ResponseEntity<>("Needy person removed successfully.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to remove needy person.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Needy person not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/NeedyPersonById/{id}")
    public NeedyPeople findNeedyPersonById(@PathVariable Long id) {
        NeedyPeople needyPerson = employeeService.findNeedyPeopleById(id);
        if (needyPerson != null) {
            return needyPerson;
        } else {
            return null;
        }
    }

    @GetMapping("/NeedyPeopleByName")
    public ResponseEntity<List<NeedyPeople>> findNeedyPeopleByName(@RequestBody String name) {
        List<NeedyPeople> needyPeople = employeeService.findNeedyPeopleByName(name);
        if (!needyPeople.isEmpty()) {
            return new ResponseEntity<>(needyPeople, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/AllNeedyPeople")
    public ResponseEntity<List<NeedyPeople>> findAllNeedyPeople() {
        List<NeedyPeople> needyPeople = employeeService.findAllNeedyPeople();
        if (!needyPeople.isEmpty()) {
            return new ResponseEntity<>(needyPeople, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}

