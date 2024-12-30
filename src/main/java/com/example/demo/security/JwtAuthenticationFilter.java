package com.example.demo.security;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal (
        @NonNull HttpServletRequest httpServletRequest,
        @NonNull HttpServletResponse httpServletResponse,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        logger.info("do filter internal is getting called...");

        String testJwt = jwtTokenProvider.generateToken("John", "Cloeter");
// Start here by getting these methods working. Might need real jwt created from this package?
        // String jwt = getJwtFromRequest(httpServletRequest);
        // String userName = jwtTokenProvider.getUsernameFromToken(testJwt);
        // String scope = jwtTokenProvider.getScopeFromToken(jwt);

        String username = "John C"

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest){
        String header = httpServletRequest.getHeader("Authorization");
        String[] token = header.split(" ");
        String jwt = token[1];
        return jwt;
    }
}
