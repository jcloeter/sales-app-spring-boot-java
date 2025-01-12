package com.example.demo.entity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority{

    @Id
    private Long id;
    private String name;
    private String displayName;
    private String description;

    @OneToMany(mappedBy = "parentRole", fetch = FetchType.EAGER)
    private List<RoleHierarchy> parentRoles;

    @OneToMany(mappedBy = "childRole", fetch = FetchType.EAGER)
    private List<RoleHierarchy> childRoles;

    @OneToMany(mappedBy = "id")
    private List<User> users;

    public Role() {
    }

    public Role(Long id, String name, String displayName, String description) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.description = description;
    }

    @Override
    public String getAuthority(){
        return this.getName();
    }

    public List<Role> getCurrentUserRoles(){
        List<Role> currentUserRoles = getChildRoles()
            .stream()
            .map(RoleHierarchy::getChildRole)
            .collect(Collectors.toList());
        currentUserRoles.add(this);
        return currentUserRoles;    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RoleHierarchy> getChildRoles() {
        return childRoles;
    }

    public void setChildRoles(List<RoleHierarchy> childRoles) {
        this.childRoles = childRoles;
    }

    public List<RoleHierarchy> getParentRoles() {
        return parentRoles;
    }

    public void setParentRoles(List<RoleHierarchy> parentRoles) {
        this.parentRoles = parentRoles;
    }


}
