package com.gestao.gestaotarefas.service;

import com.gestao.gestaotarefas.entity.Task;
import com.gestao.gestaotarefas.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;

    public Optional<Task> updateStatus(Long id, String novoStatus) {
        return repository.findById(id)
                .map(task -> {
                    task.setStatus(novoStatus);
                    return repository.save(task);
                });
    }

    public Optional<Task> updateResponsible(Long id, String newResponsible) {
        return repository.findById(id).map(task -> {
            task.setResponsible(newResponsible);
            return repository.save(task);
        });
    }

    public List<Task> listAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        return repository.findById(id);
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task newTask) {
        return repository.findById(id)
                .map(task -> {
                    task.setDescription(newTask.getDescription());
                    task.setPriority(newTask.getPriority());
                    task.setStatus(newTask.getStatus());
                    task.setResponsible(newTask.getResponsible());
                    return repository.save(task);
                });
    }

    public boolean deleteTask(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
