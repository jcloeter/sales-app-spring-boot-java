package com.example.demo.service;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.request.CreateProductRequestDto;
import com.example.demo.entity.Product;
import com.example.demo.exception.InvalidInputException;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
     
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
            .orElseThrow(() -> new InvalidInputException("No product found with id: " + id));
    }

    public Product createProduct(CreateProductRequestDto requestDto){
        Product product = new Product(requestDto);
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, CreateProductRequestDto requestDto){
        Product product = productRepository.findById(productId)
            .orElseThrow(()->new InvalidInputException("No product found with Id: " + productId));

        // Copilot suggested I do it this way:
        updateIfNotNull(requestDto.getName(), product::setName);  
        updateIfNotNull(requestDto.getDescription(), product::setDescription);    
        updateIfNotNull(requestDto.getPrice(), product::setPrice);    

        // if (requestDto.getName() != null){
        //     product.setName(requestDto.getName());
        // }   
                
        // if (requestDto.getDescription() != null){
        //     product.setDescription(requestDto.getDescription());
        // }

        // if (requestDto.getPrice() != null){
        //     product.setPrice(requestDto.getPrice());
        // }

        return productRepository.save(product);
    }

    private <T> void updateIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
