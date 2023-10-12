package com.gabrielsilva.todolist.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabrielsilva.todolist.entities.User;
import com.gabrielsilva.todolist.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserServices service;

  @GetMapping("/mensagem")
  public String primeiraMesangem() {
    return "Relou";
  }

  @PostMapping("/")
  public User create(@RequestBody User user) {
    return service.insert(user);
  }
}
