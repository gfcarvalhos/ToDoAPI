package com.gabrielsilva.todolist.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gabrielsilva.todolist.entities.User;

public interface RepositoryUser extends JpaRepository<User, UUID> {
  User findByUsername(String username); //spring data 
}
