package com.gabrielsilva.todolist.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_user")
public class User {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;  //anotação para id

  private String username;
  private String name;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public User() {
  }

  public User(String username, String name, String password) {
    this.username = username;
    this.name = name;
    this.password = password;
  }

}
