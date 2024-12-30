package com.example.demo.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final static Logger logger = Logger.getLogger(AuthController.class.getName());

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // First draft only...
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(){
        String jwt = jwtTokenProvider.generateToken("Harry", "Kane");
        logger.info(jwt);
        Boolean isValidJwt = jwtTokenProvider.validateToken(jwt);
        String username = jwtTokenProvider.getUsernameFromToken(jwt);
        logger.info(username);
        String scope = jwtTokenProvider.getScopeFromToken(jwt);

        return new ResponseEntity<>(false, HttpStatus.OK);

    }
}
