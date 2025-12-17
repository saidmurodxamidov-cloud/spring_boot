package org.example.spring_boot.entites;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TRAINEE, TRAINER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
