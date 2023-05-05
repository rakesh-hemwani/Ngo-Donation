package com.example.ngodonations.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="Donations")
@Data
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DonationID")
    private Long donationId;

    @Column(name = "DonationAmount")
    private Integer donationAmount;

    @Column(name = "DonationDate")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date donationDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    public Integer getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(Integer donationAmount) {
        this.donationAmount = donationAmount;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Donation(Integer donationAmount){
        this.donationAmount=donationAmount;
    }
}
