package com.gabrielsilva.todolist.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielsilva.todolist.entities.User;
import com.gabrielsilva.todolist.repositories.RepositoryUser;

@Service
public class UserServices {
  
  @Autowired
  private RepositoryUser repository;

  public User insert(User user){
    return repository.save(user);
  }

  public User findUser(String username){
    return repository.findByUsername(username);
    
  }
}
