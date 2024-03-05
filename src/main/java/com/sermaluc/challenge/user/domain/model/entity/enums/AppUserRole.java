package com.sermaluc.challenge.user.domain.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum AppUserRole implements GrantedAuthority {
    ADMIN, CLIENT;

    public String getAuthority() {
        return name();
    }

}

