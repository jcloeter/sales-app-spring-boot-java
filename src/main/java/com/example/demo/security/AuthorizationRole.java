package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;

public enum AuthorizationRole implements GrantedAuthority{
    
    USER("User", "Shopper"),
    MERCHANT("Merchant", "Sells and manages their products"),
    ADMIN("Admin", "Manages order and products"),
    SUPER_ADMIN("Super Admin", "Manages users, order, and products");

    private AuthorizationRole(String name, String description){
        this.name = name;
        this.description = description;
    }

    public final String name;
    public final String description;

    // TODO: This needs to be updated
    @Override
    public String getAuthority(){
        return USER.toString();
    }

}
