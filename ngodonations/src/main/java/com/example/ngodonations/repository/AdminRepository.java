package com.example.ngodonations.repository;

import com.example.ngodonations.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Long> {
}
