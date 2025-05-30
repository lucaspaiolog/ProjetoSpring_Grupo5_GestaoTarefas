package com.gestao.gestaotarefas.controller;

import com.gestao.gestaotarefas.entity.Task;
import com.gestao.gestaotarefas.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/gestao-tarefas")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<Task> getAllTasks() {
        return service.listAllTasks();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody String novoStatus) {
        return service.updateStatus(id, novoStatus)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @PatchMapping("/{id}/responsible")
    public ResponseEntity<Task> updateTaskResponsavel(@PathVariable Long id, @RequestBody String newResponsible) {
        return service.updateResponsible(id, newResponsible)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return service.findTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task newTask) {
        return service.updateTask(id, newTask)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (service.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Queries

    @GetMapping("/count-by-status")
    public Map<String, Long> countTasksByStatus() {
        List<Object[]> result = service.getTaskCountByStatus();
        Map<String, Long> response = new HashMap<>();

        for (Object[] row : result) {
            String status = (String) row[0];
            Long count = ((Number) row[1]).longValue();
            response.put(status, count);
        }

        return response;
    }

    @GetMapping("/by-priority")
    public List<Task> getByPriority(@RequestParam int priority) {
        return service.getTasksByPriority(priority);
    }

    @GetMapping("/by-responsible")
    public List<Task> getByResponsible(@RequestParam String responsible) {
        return service.getTasksByResponsible(responsible);
    }

    @GetMapping("/ordered-by-priority")
    public List<Task> getOrderedByPriority() {
        return service.getAllTasksOrderedByPriority();
    }
}
