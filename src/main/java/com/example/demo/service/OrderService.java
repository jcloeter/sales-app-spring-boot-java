package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.response.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    // Modify this return type...
    public List<OrderResponseDto> getOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> response = orders
            .stream()
            .map(OrderResponseDto::new)
            .collect(Collectors.toList());
        return response;
    }

    // public OrderResponseDto createOrder(CreateOrderRequestDto request){
        
    // }

}
