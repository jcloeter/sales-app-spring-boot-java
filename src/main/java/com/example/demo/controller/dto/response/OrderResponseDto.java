package com.example.demo.controller.dto.response;

import java.sql.Timestamp;
import java.util.List;

public class OrderResponseDto {
    private Long id;
    private Long clientId;
    private Timestamp createdOn;
    private List<ProductResponseDto> productList;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Long id, Long clientId, Timestamp createdOn, List<ProductResponseDto> productList) {
        this.id = id;
        this.clientId = clientId;
        this.createdOn = createdOn;
        this.productList = productList;
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

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<ProductResponseDto> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductResponseDto> productList) {
        this.productList = productList;
    }
}
