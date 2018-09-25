package com.mozdal.jwt.example.security;

import com.mozdal.jwt.example.model.User;
import com.mozdal.jwt.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUserWithEmail(email);
        if(user == null) {
            throw new BadCredentialsException("Username not found");
        } else {
            if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            } else {
                return new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList());
            }
        }
    }
}
