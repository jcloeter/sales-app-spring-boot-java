package com.example.demo.security;

public enum AuthorizationRole {
    
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

}
