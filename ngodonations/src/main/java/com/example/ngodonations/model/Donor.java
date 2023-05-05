package com.example.ngodonations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name="Donors")
@Data
@NoArgsConstructor
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DonorID")
    private Long donorId;

    @Column(name = "DonorName")
    private String donorName;

    @Column(name = "DonorEmail")
    private String donorEmail;

    @Column(name = "DonorPhone")
    private String donorPhone;

    @Column(name = "DonorUserName")
    private String donorUsername;

    @Column(name = "DonorPassward")
    private String donorPassword;

    @JsonManagedReference
    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations = new ArrayList<>();

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonorUsername() {
        return donorUsername;
    }

    public void setDonorUsername(String donorUsername) {
        this.donorUsername = donorUsername;
    }

    public String getDonorPassword() {
        return donorPassword;
    }

    public void setDonorPassword(String donorPassword) {
        this.donorPassword = donorPassword;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
}
