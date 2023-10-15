package com.gabrielsilva.todolist.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielsilva.todolist.entities.Task;
import java.util.List;


public interface RepositoryTask extends JpaRepository<Task, UUID> {
  List<Task> findByIdUser(UUID idUser);
}
