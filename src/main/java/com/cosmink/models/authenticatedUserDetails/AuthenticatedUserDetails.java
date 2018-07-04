package com.cosmink.models.authenticatedUserDetails;

import com.cosmink.models.authority.Authority;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

public final class AuthenticatedUserDetails implements Principal {

    private final String email;
    private final Set<Authority> authorities;

    public AuthenticatedUserDetails(String email, Set<Authority> authorities) {
        this.email = email;
        this.authorities = Collections.unmodifiableSet(authorities);
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return email;
    }
}