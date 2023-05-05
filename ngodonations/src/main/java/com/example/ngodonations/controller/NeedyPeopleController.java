package com.example.ngodonations.controller;


import com.example.ngodonations.model.NeedyPeople;
import com.example.ngodonations.model.NeedyPeopleRequest;
import com.example.ngodonations.service.NeedyPeopleService;
import com.example.ngodonations.service.NeedyPeopleServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/needyPeople")
public class NeedyPeopleController {

    @Autowired
    private NeedyPeopleServiceImpl needyPeopleService;


    @GetMapping("/allNeedyPeople")
    public List<NeedyPeople> getAllNeedyPeople(){
        return needyPeopleService.getAllNeedyPeoples();}

    @GetMapping("/allNeedyPeopleRequest")
    public List<NeedyPeopleRequest> getAllNeedyPeopleRequest(){
        return needyPeopleService.getAllNeedyPeopleRequest();
    }

    @PostMapping("/register")
    public String registerNeedyPerson(@RequestBody NeedyPeople person) {
        if (needyPeopleService.getNeedyPeopleByPhone(person.getPhone())!=null){
            return "Phone Number Already exist";
        }
        needyPeopleService.registerNeedyPerson(person);
        return "Successfully Registered";
    }

    //Login
    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> loginData) {
        String name = loginData.get("name");
        String phone = loginData.get("phone");
        if (needyPeopleService.login(name, phone)) {
            return "Login successful";
        } else {
            return "Login failed";
        }
    }

    @PostMapping("/requestHelp")
    public String requestForHelp(@RequestBody NeedyPeopleRequest request) {
        Optional<NeedyPeople> needyPeople=needyPeopleService.getNeedyById(request.getNeedyPeopleId());
        if(needyPeople.isPresent()) {
            needyPeopleService.requestForHelp(request);
            return "Successfully Request Created";
        }
        else return "You are not registered person";
    }

}
