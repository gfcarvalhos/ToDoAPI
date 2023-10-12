package com.gabrielsilva.todolist.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class Task {
  
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String descricao;

  @Column(length = 50)
  private String titulo;
  private LocalDateTime dateStart;
  private LocalDateTime dateEnd;
  private String priority;

   private UUID idUser;

  @CreationTimestamp
  private LocalDateTime createAt;

}
