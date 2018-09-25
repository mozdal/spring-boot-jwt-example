package com.mozdal.jwt.example.controller;

import com.mozdal.jwt.example.model.User;
import com.mozdal.jwt.example.service.AuthorityService;
import com.mozdal.jwt.example.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody User user) {
        JSONObject jsonObj = new JSONObject();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        authorityService.setUsersAuthoritiesFromJson(user);

        if(userService.getUserWithUsername(user.getUsername()) != null) {
            jsonObj.put("status", "Username already taken");
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        if(userService.getUserWithEmail(user.getEmail()) != null) {
            jsonObj.put("status", "Email in use");
            return new ResponseEntity<>(jsonObj, HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(user);
        jsonObj.put("status","user created successfully");
        return new ResponseEntity<>("User Created", HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<User> userList = userService.getAllUsers();

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("users", userList);
        return new ResponseEntity<>(jsonObj, HttpStatus.OK);
    }

}
