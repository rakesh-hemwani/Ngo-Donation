package com.example.ngodonations.repository;

import com.example.ngodonations.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    //DonationDao Class
}
