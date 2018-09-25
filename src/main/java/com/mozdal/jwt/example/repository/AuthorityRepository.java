package com.mozdal.jwt.example.repository;

import com.mozdal.jwt.example.model.Authority;
import org.springframework.data.repository.CrudRepository;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    Authority findAuthorityByName(String name);



}
