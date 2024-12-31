package com.example.demo.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    public String generateToken(String email){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpiration);

        String jwt = Jwts.builder()
            .setIssuer("X-sales-app-api")
            .setSubject(email)  
            .claim("scope", AuthorizationRole.ADMIN)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(
                SignatureAlgorithm.HS256,
                jwtSecret
            )
            .compact();

        return jwt;    
    }

    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        Claims claim = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

        return claim.getSubject();
        // return claim.get("foo", String.class);    
    }

    public String getScopeFromToken(String token){
        Claims claim = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody();

        return claim.get("scope", String.class);
    }
}
