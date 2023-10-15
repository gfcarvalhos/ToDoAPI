package com.gabrielsilva.todolist.controlers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielsilva.todolist.entities.Task;
import com.gabrielsilva.todolist.services.TaskServices;
import com.gabrielsilva.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskServices service;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody Task task, HttpServletRequest request) {
    task.setIdUser((UUID) request.getAttribute("idUser"));

    if (LocalDateTime.now().isAfter(task.getDateStart()) || LocalDateTime.now().isAfter(task.getDateEnd())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data de início/término deve ser maior que a data atual");
    }
    if (task.getDateStart().isAfter(task.getDateEnd())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A data de início deve ser menor que a data de término");
    }

    service.insert(task);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @GetMapping("/")
  public List<Task> findById(HttpServletRequest request) {
    return service.findById((UUID) request.getAttribute("idUser"));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Task> findByIdTask(@PathVariable UUID id) {
    Task tasks = service.findByIdTask(id);
    return ResponseEntity.status(HttpStatus.OK).body(tasks);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTask(@RequestBody Task task, @PathVariable UUID id, HttpServletRequest request) {
    Task tasks = findByIdTask(id).getBody();

    if(tasks == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada.");
    }

    var idUser = request.getAttribute("idUser");

    if (!tasks.getIdUser().equals(idUser)) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem permissão para alterar essa tarefa.");
    }

    Utils.copyNonNullProperties(task, tasks);
    var taskUpdated = service.update(tasks);
    return ResponseEntity.status(HttpStatus.OK).body(taskUpdated);
  }

}