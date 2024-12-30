package com.example.demo.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.OrderService;

// Spring Security released a new way that requires a method, SecurityFilterChain filterChain(HttpSecurity) instead of the deprecated void configure() approach.

// This config file gets executed during compilation. The Jwt Auth Filter gets executed at runtime.

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final static Logger logger = Logger.getLogger(WebSecurityConfig.class.getName());
 
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http, OrderService orderService) throws Exception {
        return http.build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // .anyRequest().permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // .httpBasic(Customizer.withDefaults()) //This uses really basic username and passwords to be sent in with the headers 

                
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // @Bean
    // private UserDetailsService userDetailsService(){
    //     UserDetails user = User.with
    // }


    // @Bean
    // public UserDetailsService userDetailsService(){
    //     // It is deprecated but just using this locally for now

    //     UserDetails user;
    //     user = User.withDefaultPasswordEncoder()
    //             .username("admin")
    //             .password("admin123")
    //             .roles("USER")
    //             .build();
            
    //         return new InMemoryUserDetailsManager(user);  
    // }

}
