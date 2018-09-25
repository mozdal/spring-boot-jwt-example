package com.mozdal.jwt.example.service;

import com.mozdal.jwt.example.model.Authority;
import com.mozdal.jwt.example.model.User;
import com.mozdal.jwt.example.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Authority findAuthorityWithName(String name) {
        Authority auth = authorityRepository.findAuthorityByName(name);
        return auth;
    }

    public User setUsersAuthoritiesFromJson(User user) {
        List<Authority> authList = user.getAuthorities();
        for(int i = 0; i < user.getAuthorities().size(); i++) {
            Authority auth = findAuthorityWithName(user.getAuthorities().get(i).getName());
            if(auth != null) {
                authList.set(i, auth);
            }
        }
        user.setAuthorities(authList);
        return user;
    }

}