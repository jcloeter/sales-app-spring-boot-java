package com.example.demo.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UnauthorizedException;
import com.example.demo.repository.UserRepository;

@Service()
public class UserDetailServiceImpl implements UserDetailsService{

    UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email){
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()){
            // throw new Unauthorized error
            throw new UnauthorizedException("No user found");
        }

        return new UserDetailImpl(
            user.get().getEmail(),
            user.get().getPassword(),
            new ArrayList<>(Arrays.asList(AuthorizationRole.USER))
        );
    }
}
