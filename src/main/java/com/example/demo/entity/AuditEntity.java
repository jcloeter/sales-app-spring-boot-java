package com.example.demo.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
public class AuditEntity {

    // Start here, might need to add the Serializeable class?
    // Right now created_at gets returned as NULL even when it exists in the db
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Timestamp getCreatedAt(){ return this.createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
