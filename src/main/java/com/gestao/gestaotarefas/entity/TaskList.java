package com.gestao.gestaotarefas.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference; // <-- IMPORT THIS
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TaskList {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // <-- ADD THIS ANNOTATION
    private List<Task> tasks;
}
