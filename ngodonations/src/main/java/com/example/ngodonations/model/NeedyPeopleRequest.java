package com.example.ngodonations.model;

import jakarta.persistence.*;

@Entity
@Table(name="NeedyPeopleRequest")
public class NeedyPeopleRequest {
    @Id
    @Column(name = "NeedyPersonId")
    Long needyPeopleId;

    @Column(name = "NeedyPersonName")
    String name;

    @Column(name="RequestAmount")
    Integer moneyRequest;

    public Integer getMoneyRequest() {
        return moneyRequest;
    }

    public void setMoneyRequest(Integer moneyRequest) {
        this.moneyRequest = moneyRequest;
    }

    public NeedyPeopleRequest() {

    }

    public Long getNeedyPeopleId() {
        return needyPeopleId;
    }

    public void setNeedyPeopleId(Long needyPeopleId) {
        this.needyPeopleId = needyPeopleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
