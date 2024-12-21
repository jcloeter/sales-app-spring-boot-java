package com.example.demo.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;

public class AuditEntity {

    @Column(name = "created_on")
    private Timestamp createdOn;

    public Timestamp getCreatedOn(){ return this.createdOn; }

    public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
}
