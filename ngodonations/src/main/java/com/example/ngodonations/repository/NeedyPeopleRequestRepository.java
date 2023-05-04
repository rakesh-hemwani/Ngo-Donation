package com.example.ngodonations.repository;

import com.example.ngodonations.model.NeedyPeopleRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeedyPeopleRequestRepository  extends JpaRepository<NeedyPeopleRequest, Long> {
}
