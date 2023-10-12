package com.gabrielsilva.todolist.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielsilva.todolist.entities.User;
import com.gabrielsilva.todolist.repositories.RepositoryUser;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserServices {
  
  @Autowired
  private RepositoryUser repository;

  public User insert(User user){
    user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
    return repository.save(user);
  }

  public User findUser(String username){
    return repository.findByUsername(username);
    
  }
}
