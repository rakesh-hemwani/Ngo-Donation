package com.example.ngodonations.model;

import jakarta.persistence.*;

@Entity
@Table(name="NeedyPeople")
public class NeedyPeople {
    @Id
    @Column(name = "NeedyPersonId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long needyPersonId;

    @Column(name = "Name")
    private String needyPersonName;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Income")
    private double familyIncome;


    public Long getNeedyPersonId() {
        return needyPersonId;
    }

    public void setNeedyPersonId(Long needyPersonId) {
        this.needyPersonId = needyPersonId;
    }

    public String getNeedyPersonName() {
        return needyPersonName;
    }

    public void setNeedyPersonName(String needyPersonName) {
        this.needyPersonName = needyPersonName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getFamilyIncome() {
        return familyIncome;
    }

    public void setFamilyIncome(double familyIncome) {
        this.familyIncome = familyIncome;
    }


    public NeedyPeople() {

    }

    public NeedyPeople(Long needyPersonId, String needyPersonName, String phone, double familyIncome) {
        super();
        this.needyPersonId = needyPersonId;
        this.needyPersonName = needyPersonName;
        this.phone = phone;
        this.familyIncome = familyIncome;
    }

    @Override
    public String toString() {
        return "NeedyPeople [needyPersonId=" + needyPersonId + ", needyPersonName=" + needyPersonName + ", phone="
                + phone + ", familyIncome=" + familyIncome + ", address="  + "]";
    }



}
