package com.gabrielsilva.todolist.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity create(@RequestBody User user) {
    User userTeste = service.findUser(user.getUsername());
    if(userTeste != null){
      System.out.println("Usuário já existe.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
    }
    User userCreated = service.insert(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }
}
