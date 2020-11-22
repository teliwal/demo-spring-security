package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    EMPLOYEE(Collections.emptySet()),
    RESPONSIBLE(Set.of(EMPLOYEE_READ,EMPLOYEE_HIRE)),
    ADMIN(Set.of(EMPLOYEE_READ,EMPLOYEE_HIRE,EMPLOYEE_FIRE));

    private Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authorithies = this.permissions
                .stream().map(p -> new SimpleGrantedAuthority(p.getPermission()))
                .collect(Collectors.toSet());
        authorithies.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        System.out.println(this.name() + authorithies);
        return authorithies;
    }
}
