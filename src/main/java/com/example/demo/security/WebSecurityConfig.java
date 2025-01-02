package com.example.demo.security;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers("/api/auth/login").permitAll()
                // .antMatchers("/api/auth/login").permitAll() // Allow login endpoint
                // Example:
                // .requestMatchers("/endpoint").hasAuthority("USER")

                // These two work decently well:
                // .requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll()
                // .anyRequest().authenticated()

                .anyRequest().permitAll()
        )    
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // .httpBasic(Customizer.withDefaults()) //This uses really basic username and passwords to be sent in with the headers 
                
        // http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
