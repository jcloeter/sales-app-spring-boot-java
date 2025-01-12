package com.example.demo.controller;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.request.LoginRequestDto;
import com.example.demo.controller.dto.response.LoginResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final static Logger logger = Logger.getLogger(AuthController.class.getName());

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository){
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        logger.info(String.format("Login request sent in for user %s", loginRequestDto.getEmail()));

        try {
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
            );
            // This auth object contains info about the user.
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

            Long tokenExpiration = jwtTokenProvider.getTokenExpirationLength(); // 60 minutes
            String token = jwtTokenProvider.generateToken(loginRequestDto.getEmail(), user.get().getRole().getName());
            String tokenType = "Bearer";
            String refreshToken = "";

            return new ResponseEntity<>(new LoginResponseDto(token, tokenType, tokenExpiration, refreshToken), HttpStatus.OK);
        } catch(Exception e){
            logger.info(String.format("There was an error logging in user %s: %s", loginRequestDto.getEmail(), e.getMessage()));

            throw new UnauthorizedException("Invalid username or password");
        }
        
    }

}
