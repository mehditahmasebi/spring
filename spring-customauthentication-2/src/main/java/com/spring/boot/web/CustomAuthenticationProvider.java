package com.spring.boot.web;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomMyAuthenticationToken cutomAuthenticationToken = (CustomMyAuthenticationToken) authentication;
        ComplexCredentials credentials = (ComplexCredentials) cutomAuthenticationToken.getCredentials();
        if (credentials.getPassword().equals("admin") && credentials.getPasswordSecond().equals("admin2")){
            cutomAuthenticationToken.setAuthenticated(true);
            return cutomAuthenticationToken;
        }else{
            throw new BadCredentialsException("Username or password was incorrect.");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return CustomMyAuthenticationToken.class.isAssignableFrom(authentication);
    }
}