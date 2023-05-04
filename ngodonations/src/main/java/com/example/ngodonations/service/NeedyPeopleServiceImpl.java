package com.example.ngodonations.service;

import com.example.ngodonations.exceptions.DuplicateDonorException;
import com.example.ngodonations.model.Donation;
import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.NeedyPeople;
import com.example.ngodonations.model.NeedyPeopleRequest;
import com.example.ngodonations.repository.NeedyPeopleRepository;
import com.example.ngodonations.repository.NeedyPeopleRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NeedyPeopleServiceImpl implements NeedyPeopleService{


    @Autowired
    NeedyPeopleRepository needyPeopleRepository;

    @Autowired
    NeedyPeopleRequestRepository needyPeopleRequestRepository;


    //helper
    public Optional <NeedyPeople> getNeedyById(Long id){
        return needyPeopleRepository.findById(id);
    }
    public List<NeedyPeople> getAllNeedyPeoples(){
        return needyPeopleRepository.findAll();
    }
    public void removeNeedyPeopleById(Long id){
        needyPeopleRepository.deleteById(id);
    }
    public NeedyPeople getNeedyPeopleByPhone(String phone) {
        return needyPeopleRepository.findNeedyPeopleByPhone(phone);
    }

    public List<NeedyPeopleRequest> getAllNeedyPeopleRequest(){
        return needyPeopleRequestRepository.findAll();
    }


    //main chunk

    @Override
    public boolean registerNeedyPerson(NeedyPeople person) {
        needyPeopleRepository.save(person);
        return true;
    }

    @Override
    public boolean login(String Name, String Phone) {
        NeedyPeople needyPeople=needyPeopleRepository.findByNeedyPersonName(Name);
        if(needyPeople!=null && needyPeople.getPhone().equals(Phone)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean requestForHelp(NeedyPeopleRequest request) {
        needyPeopleRequestRepository.save(request);
        return true;
    }

    public <NeedyPerson> NeedyPerson getNeedyPersonByName(String name) {
        needyPeopleRepository.findByNeedyPersonName(name);
        return null;
    }

}
