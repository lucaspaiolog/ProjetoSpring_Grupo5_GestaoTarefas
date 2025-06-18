package com.gestao.gestaotarefas.controller;

import com.gestao.gestaotarefas.entity.Task;
import com.gestao.gestaotarefas.entity.TaskList;
import com.gestao.gestaotarefas.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gestao-tarefas/lists")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService service;

    @GetMapping
    public List<TaskList> getAllTaskLists() {
        return service.getAllTaskLists();
    }
    @PatchMapping("/{id}/name")
    public ResponseEntity<TaskList> updateTaskListName(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String newName = request.get("name");
        if (newName == null || newName.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return service.updateTaskListName(id, newName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable Long id) {
        service.deleteTaskList(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByListId(@PathVariable Long id) {
        return service.getTasksByListId(id);
    }

    @PostMapping("/{listId}/tasks")
    public void addTaskToList(@PathVariable Long listId, @RequestBody Long taskId) {
        service.addTaskToList(listId, taskId);
    }

    @DeleteMapping("/{listId}/tasks/{taskId}")
    public void removeTaskFromList(@PathVariable Long listId, @PathVariable Long taskId) {
        service.removeTaskFromList(listId, taskId);
    }

    @GetMapping("/{listId}/tasks/{taskId}")
    public Task getTaskByIdInList(@PathVariable Long listId, @PathVariable Long taskId) {
        return service.getTaskByIdInList(listId, taskId);
    }

    @GetMapping("/{listId}/tasks/allTasks")
    public List<Task> getAllTasksInList(@PathVariable Long listId) {
        return service.getAllTasksInList(listId);
    }

    @GetMapping("/{listId}/tasks/filterByStatus")
    public List<Task> getTasksFilteredByStatus(@PathVariable Long listId, @RequestParam String status) {
        return service.getTasksFilteredByStatus(listId, status);
    }

    @GetMapping("/{listId}/tasks/filterByResponsible")
    public List<Task> getTasksFilteredByResponsible(@PathVariable Long listId, @RequestParam String responsible) {
        return service.getTasksFilteredByResponsible(listId, responsible);
    }

    @GetMapping("/{listId}/tasks/filterByPriority")
    public List<Task> getTasksFilteredByPriority(@PathVariable Long listId, @RequestParam int priority) {
        return service.getTasksFilteredByPriority(listId, priority);
    }
    @PostMapping
    public void createTaskList(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        service.createTaskList(name);
    }


}
