package com.example.demo.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.controller.dto.request.CreateUserRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final static Logger logger = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(CreateUserRequestDto requestDto){
        logger.info("Creating user with email " + requestDto.getEmail());

        Optional<User> existingUser = userRepository.findByEmail(requestDto.getEmail());

        if (existingUser.isPresent()){
            throw new IllegalArgumentException(String.format("User with email %s already exists", requestDto.getEmail()));
        }

        User user = new User(requestDto);
        String encodedPassword = bCryptPasswordEncoder.encode(requestDto.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }
}
