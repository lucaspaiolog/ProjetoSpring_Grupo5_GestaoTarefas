package com.gestao.gestaotarefas.entity;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- IMPORT THIS
import jakarta.persistence.*;
import lombok.Data;

@Table(name= "task")
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int priority;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String responsible;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    @JsonBackReference // <-- ADD THIS ANNOTATION
    private TaskList taskList;
}
