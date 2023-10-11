package com.gabrielsilva.todolist.entities;

public class User {
  private String username;
  private String name;
  private String password;

  public User() {
  }

  public User(String username, String name, String password) {
    this.username = username;
    this.name = name;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  

}
