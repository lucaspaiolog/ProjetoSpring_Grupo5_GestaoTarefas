package com.gestao.gestaotarefas.controller;

import com.gestao.gestaotarefas.entity.Task;
import com.gestao.gestaotarefas.service.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gestao-tarefas/lists")
@RequiredArgsConstructor
public class TaskListController {

    private final TaskListService service;

    @DeleteMapping("/{id}")
    public void deleteTaskList(@PathVariable Long id) {
        service.deleteTaskList(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTasksByListId(@PathVariable Long id) {
        return service.getTasksByListId(id);
    }

    @PostMapping
    public void addTaskToList(@RequestParam Long listId, @RequestBody Long taskId) {
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


}
