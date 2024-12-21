package com.example.demo.controller.dto.request;

public class CreateSaleRequestDto {
    private String productName;
    private Long quantity;

    public CreateSaleRequestDto(
        String productName,
        Long quantity
    ){
        this.productName = productName;
        this.quantity = quantity;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getProductName(){
        return this.productName;
    }

    public void setQuantity(Long quantity){
        this.quantity = quantity;
    }

    public Long getQuantity(){
        return this.quantity;
    }

}
