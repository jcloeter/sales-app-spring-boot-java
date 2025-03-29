package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.request.CreateOrderRequestDto;
import com.example.demo.controller.dto.response.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    // Note: Use @PathVariable, not @PathParam
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(new OrderResponseDto(order), HttpStatus.OK);
    }

    @Secured("ROLE_SUPER_ADMIN")
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

    @Secured("ROLE_ADMIN")
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

    // TODO: 
        // Add scope so users can only get orders, update orders, or create orders for themselves
        // Add authorization so only admin users can see all orders
        // Allow only admins to update Products     
    
}
