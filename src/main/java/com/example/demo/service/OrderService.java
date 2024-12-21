package com.example.demo.service;

import java.util.List;

import com.example.demo.controller.dto.request.CreateOrderRequestDto;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public List<OrderResponseDto> getOrders(){
        return orderRepository.findAll();
    }

    public OrderResponseDto createOrder(CreateOrderRequestDto request){
        
    }

}
