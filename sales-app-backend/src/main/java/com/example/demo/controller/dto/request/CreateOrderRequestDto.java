package com.example.demo.controller.dto.request;

import java.util.List;

public class CreateOrderRequestDto {
    private final Long userId;

    private final List<Long> productIds;

    public CreateOrderRequestDto(Long userId, List<Long> productIds){
        this.userId = userId;
        this.productIds = productIds;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }


}
