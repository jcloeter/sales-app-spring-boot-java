package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.request.CreateOrderRequestDto;
import com.example.demo.controller.dto.response.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class OrderService {

    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    
    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(Long orderId){
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()){
            LOGGER.warning("No order found for id: " + orderId);
            throw new InvalidInputException("No order found for id: " + orderId);
        } else {
            return order.get();
        }
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

        if (request.getProductIds().isEmpty()){
            throw new InvalidInputException("An order must contain one or more products.");
        }

        List<Product> products = productRepository.findAllById(request.getProductIds());

        if (products.size() != request.getProductIds().size()){
            throw new ProductNotFoundException("One of more product Ids were invalid.");
        }

        order.setProducts(products);

        Order savedOrder = orderRepository.saveAndFlush(order);
        return new OrderResponseDto(savedOrder);
    }

    public OrderResponseDto updateOrder(Long orderId, CreateOrderRequestDto request){
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()){
            LOGGER.log(Level.SEVERE, "Invalid order id: {0}", orderId);
            throw new OrderNotFoundException("Order Id must be for a valid Order.");
        }

        if (request.getUserId() != null ){
            order.get().setUserId(request.getUserId());
        }

        if (!request.getProductIds().isEmpty()){
            List<Product> products = productRepository.findAllById(request.getProductIds());

            if (request.getProductIds().size() != products.size()){

                List<Long> invalidProductIds = request.getProductIds()
                    .stream()
                    .filter(productId -> {
                        Boolean productIdIsInvalid = true;

                        for (Product product: products){
                            if (product.getId().equals(productId)){
                                productIdIsInvalid = false;
                            }
                        }

                        return productIdIsInvalid;
                    })
                    .collect(Collectors.toList());

                LOGGER.log(Level.INFO, "Invalid product ids: {0}", invalidProductIds);

                throw new ProductNotFoundException("One or more productIds were invalid.");
            }

            order.get().setProducts(products);
        }

        Order updatedOrder = orderRepository.save(order.get());
        return new OrderResponseDto(updatedOrder);
    }

}
