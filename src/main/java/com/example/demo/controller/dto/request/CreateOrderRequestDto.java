package com.example.demo.controller.dto.request;

import java.util.List;

public class CreateOrderRequestDto {
    private final Long clientId;

    private final List<Long> productIds;

    public CreateOrderRequestDto(Long clientId, List<Long> productIds){
        this.clientId = clientId;
        this.productIds = productIds;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<Long> getProductIds() {
        return productIds;
    }


}
