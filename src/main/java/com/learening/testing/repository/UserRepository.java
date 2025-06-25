package com.learening.testing.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learening.testing.model.AppUser;


public interface UserRepository  extends MongoRepository<AppUser, String> {
 Optional<AppUser> findByUsername(String username);
    
    

}
