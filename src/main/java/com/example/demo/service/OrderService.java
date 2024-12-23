package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.request.CreateOrderRequestDto;
import com.example.demo.controller.dto.response.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderResponseDto> getOrders(){
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> response = orders
            .stream()
            .map(OrderResponseDto::new)
            .collect(Collectors.toList());
        return response;
    }

    public OrderResponseDto createOrder(CreateOrderRequestDto request){

        Order order = new Order();

        order.setUserId(request.getUserId());
        List<Product> products = productRepository.findAllById(request.getProductIds());
        if (products.isEmpty()){
            throw new IllegalArgumentException("Must specify a valid product id.");
        }

        order.setProducts(products);

        Order savedOrder = orderRepository.saveAndFlush(order);
        return new OrderResponseDto(savedOrder);
    }

}
