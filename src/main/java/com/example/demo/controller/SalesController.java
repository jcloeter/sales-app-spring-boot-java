package com.example.demo.controller;

import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.request.CreateSaleRequestDto;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @GetMapping
    public ResponseEntity<String> getSales(){
        System.out.println("Request made to sales rest api...");
        return new ResponseEntity<String>("No Sales!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createSale(
        @RequestBody CreateSaleRequestDto requestDto
    ){
        System.out.println(requestDto.getProductName());
        return new ResponseEntity<String>("Sale was created", HttpStatus.CREATED);
    }
}
