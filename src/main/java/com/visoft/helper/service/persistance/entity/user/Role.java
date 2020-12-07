package com.visoft.helper.service.persistance.entity.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    CLIENT,
    ;

    @Override
    public String getAuthority() {
        return name();
    }
}
