package com.example.ngodonations.service;

import com.example.ngodonations.exceptions.NoSuchEmployeeException;
import com.example.ngodonations.model.NeedyPeople;

import java.util.List;

public interface EmployeeService {
    public boolean login(String Username, String Password)throws NoSuchEmployeeException;
    public boolean addNeedyPerson(NeedyPeople person);
    public boolean removeNeedyPerson(NeedyPeople person);
    public NeedyPeople findNeedyPeopleById(Long id);
    public List<NeedyPeople> findNeedyPeopleByName(String name);
    public List<NeedyPeople> findAllNeedyPeople();
}
