package com.example.ngodonations.repository;

import com.example.ngodonations.model.Donor;
import com.example.ngodonations.model.Employee;
import com.example.ngodonations.model.NeedyPeople;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NeedyPeopleRepository  extends JpaRepository<NeedyPeople, Long> {
    NeedyPeople findByNeedyPersonName(String name);
    NeedyPeople findNeedyPeopleByPhone(String phone);
    List<NeedyPeople> getAllByNeedyPersonName(String name);
}
