package com.example.demo.controller.dto.request;

import java.math.BigDecimal;

import com.example.demo.entity.Product;

public class CreateProductRequestDto {
    private String name;

    private String description;

    private BigDecimal price;

    public CreateProductRequestDto(){}

    public CreateProductRequestDto(String name, String description, BigDecimal price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public CreateProductRequestDto(Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }    
}
