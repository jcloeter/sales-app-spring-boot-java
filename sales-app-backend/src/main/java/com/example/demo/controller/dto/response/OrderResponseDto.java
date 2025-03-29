package com.example.demo.controller.dto.response;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Order;

public class OrderResponseDto {
    private Long id;
    private Long clientId;
    private Timestamp createdAt;
    private List<ProductResponseDto> products;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, Long clientId, Timestamp createdAt, List<ProductResponseDto> products) {
        this.id = id;
        this.clientId = clientId;
        this.createdAt = createdAt;
        this.products = products;
    }

    public OrderResponseDto(Order order){
        this.id = order.getId();
        this.clientId = order.getUserId();
        this.createdAt = order.getCreatedAt();
        this.products = order.getProducts()
                .stream()
                .map(ProductResponseDto::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ProductResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponseDto> products) {
        this.products = products;
    }
}
