package com.gabrielsilva.todolist.controlers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielsilva.todolist.entities.Task;
import com.gabrielsilva.todolist.services.TaskServices;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskServices service;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
    task.setIdUser((UUID) request.getAttribute("idUser"));
    
    if(LocalDateTime.now().isAfter(task.getDateStart()) || LocalDateTime.now().isAfter(task.getDateEnd())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início/término deve ser maior que a data atual");
    }
    if(task.getDateStart().isAfter(task.getDateEnd())){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de início deve ser menor que a data de término");
    }

    service.insert(task);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

}