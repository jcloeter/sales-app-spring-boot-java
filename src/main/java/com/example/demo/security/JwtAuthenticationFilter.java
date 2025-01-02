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

import com.example.demo.exception.UnauthorizedException;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private static final Logger logger = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    private final JwtTokenProvider jwtTokenProvider;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService){
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal (
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            logger.info("do filter internal is getting called...");

            // String testJwt = jwtTokenProvider.generateToken("j@gmail.com");
    // Start here by getting these methods working. Might need real jwt created from this package?
            String jwt = getJwtFromRequest(request);
            String email = jwtTokenProvider.getUsernameFromToken(jwt);
            String scope = jwtTokenProvider.getScopeFromToken(jwt);

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
        }        
    }

    // This worked, but I want to try and centralize this logic to the config class:
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
        throws ServletException {
        String path = request.getRequestURI();

        return "/api/user".equals(path) || "/api/auth/login".equals(path);
    }

    private String getJwtFromRequest(HttpServletRequest httpServletRequest){
        try {
            String header = httpServletRequest.getHeader("Authorization");    
            String[] token = header.split(" ");
            String jwt = token[1];
            return jwt;
        } catch(Exception e){
            throw new UnauthorizedException("Must include JWT in Authorization header with 'Bearer'.");
        }
    }
}
