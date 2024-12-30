package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.request.CreateProductRequestDto;
import com.example.demo.controller.dto.response.ProductResponseDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

// RestController directly maps data to http responses. It basically applies an @RequestBody annotation to every method, which serializes data to the response body.
// @Controller should be used when data could be sent as another form, such as html

// @GetMapping, @PostMapping, etc are all shortcuts for another way of writing requests like this:
// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
// This is why we still need @RequestMapping in addition to @RestController

@RestController() 
@RequestMapping("/api/product")
public class ProductController {
    
    private final ProductService productService;
    
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<Product> products = productService.getAllProducts();

        List<ProductResponseDto> productResponseDto = products
            .stream()
            .map(ProductResponseDto::new)
            .collect(Collectors.toList());

        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(new ProductResponseDto(product), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(
        @RequestBody CreateProductRequestDto requestDto) {
        Product product = productService.createProduct(requestDto);    
        return new ResponseEntity<>(new ProductResponseDto(product), HttpStatus.CREATED);
    }   

    // PUT vs PATCH depends on partial update or not...
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long id, 
        @RequestBody CreateProductRequestDto requestDto
    ){
        Product product = productService.updateProduct(id, requestDto);
        return new ResponseEntity<>(new ProductResponseDto(product), HttpStatus.OK);
    }

    
    // TODO: Add ability to search, paginate, and filter here.
    // Add a parent controller to add /api in front
}
