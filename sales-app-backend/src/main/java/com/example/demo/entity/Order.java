package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "orders")
public class Order extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToMany
    @JoinTable(
        name = "product_order",
        // The column in product_order that references this entity
        joinColumns = @JoinColumn(name = "order_id"), 
        // The column in product_order that references the other entity 
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public Order(){}

    public void setId(Long id){ this.id = id; }

    public Long getId() { return this.id; }
    
    public void setUserId(Long userId){ this.userId = userId; }

    public Long getUserId(){ return this.userId; }

    public List<Product> getProducts(){ return this.products; }

    public void setProducts(List<Product> products){ this.products = products; }

}
