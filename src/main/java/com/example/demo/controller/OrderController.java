package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.request.CreateOrderRequestDto;
import com.example.demo.controller.dto.response.OrderResponseDto;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(){
        return orderService.getOrders();
    }

    @PostMapping
    public OrderResponseDto createOrder(
        @RequestBody CreateOrderRequestDto request
    ){
        return orderService.createOrder(request);
    }
}
