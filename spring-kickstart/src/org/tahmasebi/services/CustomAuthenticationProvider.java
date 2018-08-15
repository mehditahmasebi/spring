package org.tahmasebi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
    public CustomAuthenticationProvider() {
        super();
    }

	@Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final String name = authentication.getName();
        final String password = authentication.getCredentials().toString();
        final List<GrantedAuthority> grantedAuths = new ArrayList<>();
        
    	if(!(name.equals("admin") && password.equals("admin")) && 
    			!(name.equals("user") && password.equals("user")))
			throw new RuntimeException("username or password is incorrect");
    	if(name.equals("admin"))
    	{
    		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
    		grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    	}
    	else
    		grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
    	
        final UserDetails principal = new User(name, password, grantedAuths);
        final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        return auth;
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}