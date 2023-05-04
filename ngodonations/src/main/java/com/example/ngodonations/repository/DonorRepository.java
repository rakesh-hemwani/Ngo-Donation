package com.example.ngodonations.repository;

import com.example.ngodonations.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    //DonorDao Class
    Donor findByDonorEmail(String email);
}
