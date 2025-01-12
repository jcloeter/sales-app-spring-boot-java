package com.example.demo.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Role;

public class UserDetailImpl implements UserDetails{

    private String email;
    private String password;
    private List<Role> authorities;

    public UserDetailImpl(String email, String password, List<Role> authorities){
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public String getUsername(){
        // Using email as the unique username
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }
}
