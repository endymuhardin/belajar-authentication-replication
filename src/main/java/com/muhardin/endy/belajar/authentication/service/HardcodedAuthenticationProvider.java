package com.muhardin.endy.belajar.authentication.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class HardcodedAuthenticationProvider implements AuthenticationProvider {

    private static final List<GrantedAuthority> permissions 
        = Arrays.asList(new SimpleGrantedAuthority("VIEW_USER_INFO"));

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        log.info("Username : {}", username);
        log.info("Password : {}", password);
        if(StringUtils.hasText(username) && StringUtils.hasText(password) && password.equals(username+"123")) {
            return new UsernamePasswordAuthenticationToken(username, password, permissions);
        }
        throw new BadCredentialsException("Invalid Username/Password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
