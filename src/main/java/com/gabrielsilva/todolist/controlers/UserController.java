package com.gabrielsilva.todolist.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gabrielsilva.todolist.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {

  @GetMapping("/mensagem")
  public String primeiraMesangem() {
    return "Relou";
  }

  @PostMapping("/")
  public void create(@RequestBody User user) {
    System.out.println(user.getUsername());
  }
}
