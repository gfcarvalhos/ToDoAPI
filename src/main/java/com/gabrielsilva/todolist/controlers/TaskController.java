package com.gabrielsilva.todolist.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielsilva.todolist.entities.Task;
import com.gabrielsilva.todolist.services.TaskServices;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private TaskServices service;

  @PostMapping("/")
  public Task create(@RequestBody Task task){
    return service.insert(task);
  }

}