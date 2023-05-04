package com.example.ngodonations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Admins")
public class Admin {
    @Id
    @Column(name = "AdminID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long adminId;

    @Column(name = "AdminUserName")
    private String adminUsername;

    @Column(name = "AdminPassward")
    private String adminPassword;

    @JsonManagedReference
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin() {

    }


    public Admin(Long adminId, String adminUsername, String adminPassword) {
        super();
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
                + "]";
    }


}