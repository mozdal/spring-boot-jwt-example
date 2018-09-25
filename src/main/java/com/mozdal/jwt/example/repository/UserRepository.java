package com.mozdal.jwt.example.repository;

import com.mozdal.jwt.example.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    List<User> findAll();

}
