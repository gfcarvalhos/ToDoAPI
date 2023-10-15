package com.gabrielsilva.todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielsilva.todolist.entities.Task;
import com.gabrielsilva.todolist.repositories.RepositoryTask;

@Service
public class TaskServices {

  @Autowired
  private RepositoryTask repository;

  public Task insert(Task task){
    return repository.save(task);
  }

  public List<Task> findById(UUID id){
    return repository.findByIdUser(id);
  }

  public Task findByIdTask(UUID id){
    Optional<Task> obj = repository.findById(id);
    return obj.get();
  }

  public Task update(Task task){
    return repository.save(task);
  }
}
