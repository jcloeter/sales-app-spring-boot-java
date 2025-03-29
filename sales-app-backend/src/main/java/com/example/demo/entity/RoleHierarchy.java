package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "role_hierarchy")
public class RoleHierarchy {
    
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_role_id", insertable = false, updatable = false)
    private Role parentRole;

    @ManyToOne
    @JoinColumn(name = "child_role_id", insertable = false, updatable = false)
    private Role childRole;
    
    public RoleHierarchy() {
    }

    public Role getParentRole() {
        return parentRole;
    }

    public void setParentRole(Role parentRole) {
        this.parentRole = parentRole;
    }

    public Role getChildRole() {
        return childRole;
    }

    public void setChildRole(Role childRole) {
        this.childRole = childRole;
    }
}
