package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
        @PathVariable Long id,
        @RequestBody CreateOrderRequestDto request
    ){
        OrderResponseDto response = orderService.updateOrder(id, request);

        // ResponseEntity can contain whatever object body you want
        // AND it takes an HttpStatus object as well
        return new ResponseEntity<>(response, HttpStatus.OK);
    }   

    // Update Order- only for current user though OR if user is admin they can update anyone's

    // Get Order By Id
    
}
