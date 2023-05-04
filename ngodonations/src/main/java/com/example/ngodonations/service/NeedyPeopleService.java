package com.example.ngodonations.service;

import com.example.ngodonations.model.NeedyPeople;
import com.example.ngodonations.model.NeedyPeopleRequest;

public interface NeedyPeopleService {
    public boolean registerNeedyPerson(NeedyPeople person);
    public boolean login(String Name, String Phone);
    public boolean requestForHelp(NeedyPeopleRequest person);
}
