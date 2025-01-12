package com.example.demo.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Spring Security released a new way that requires a method, SecurityFilterChain filterChain(HttpSecurity) instead of the deprecated void configure() approach.

// This config file gets executed during compilation. The Jwt Auth Filter gets executed at runtime.

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // Allows us to use @Secured?
public class WebSecurityConfig {

    private final static Logger logger = Logger.getLogger(WebSecurityConfig.class.getName());
 
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private UserDetailsService userDetailsService;  

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/auth/login").permitAll()
            .anyRequest().permitAll()
            // .requestMatchers("/api/order/**").permitAll()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }

    // @Bean
    // public RoleHierarchyImpl roleHierarchy() {
    //     String hierarchy = "ROLE_SUPER_ADMIN > ROLE_ADMIN\nROLE_ADMIN > ROLE_MERCHANT\nROLE>MERCHANT >ROLE_USER";
    //     RoleHierarchyImpl roleHierarchy = RoleHierarchyImpl.fromHierarchy(hierarchy);
    //     return roleHierarchy;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
