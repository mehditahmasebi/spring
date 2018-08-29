package com.spring.boot.web;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomMyAuthenticationToken implements Authentication {
	private static final long serialVersionUID = 1L;
	private final String principalName;
    private Object credentials;
    private boolean authenticated;

    public CustomMyAuthenticationToken(String principalName, Object credentials) {
        this.principalName = principalName;
        this.credentials = credentials;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LinkedList<GrantedAuthority> result = new LinkedList<>();
        result.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return result;
    }
    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principalName;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.principalName;
    }
}
