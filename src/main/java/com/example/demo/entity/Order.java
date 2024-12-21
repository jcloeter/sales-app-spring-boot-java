package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Order extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    // @Column(name = "created_on")
    // private Timestamp createdOn;

    public Order(){}

    // public Order(Long userId, Timestamp createdOn){
    //     this.userId = userId;
    //     this.createdOn = createdOn;
    // }

    public void setId(Long id){ this.id = id; }

    public Long getId() { return this.id; }
    
    public void setUserId(Long userId){ this.userId = userId; }

    public Long getUserId(){ return this.userId; }

    // public Timestamp getCreatedOn(){ return this.createdOn; }

    // public void setCreatedOn(Timestamp createdOn) { this.createdOn = createdOn; }
}
