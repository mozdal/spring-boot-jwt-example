package com.mozdal.jwt.example.service;

import com.mozdal.jwt.example.model.Authority;
import com.mozdal.jwt.example.model.User;
import com.mozdal.jwt.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User getUserWithUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    public User getUserWithEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Authority getUsersAuthorityWithEmail(String email) {
        User user = getUserWithEmail(email);
        return user.getAuthorities().get(0);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
