package com.gestao.gestaotarefas.service;

import com.gestao.gestaotarefas.entity.Task;
import com.gestao.gestaotarefas.entity.TaskList;
import com.gestao.gestaotarefas.repository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.gestao.gestaotarefas.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final TaskRepository TaskRepository;

    public void deleteTaskList(Long id) {
        if (taskListRepository.existsById(id)) {
            taskListRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Task list with id " + id + " does not exist.");
        }
    }

    public List<Task> getTasksByListId(Long listId) {
        return taskListRepository.findById(listId)
                .map(TaskList::getTasks)
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }

    public void addTaskToList(Long listId, Long taskId) {
        Task task = TaskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + taskId + " does not exist."));
        TaskList taskList = taskListRepository.findById(listId)
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));

        task.setTaskList(taskList);
        taskList.getTasks().add(task);

        TaskRepository.save(task);
        taskListRepository.save(taskList);
    }


    public void removeTaskFromList(Long listId, Long taskId) {
        taskListRepository.findById(listId)
                .ifPresentOrElse(taskList -> {
                    taskList.getTasks().removeIf(task -> task.getId().equals(taskId));
                    taskListRepository.save(taskList);
                }, () -> {
                    throw new IllegalArgumentException("Task list with id " + listId + " does not exist.");
                });
    }

    public Task getTaskByIdInList(Long listId, Long taskId) {
        return taskListRepository.findById(listId)
                .map(taskList -> taskList.getTasks().stream()
                        .filter(task -> task.getId().equals(taskId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Task with id " + taskId + " not found in list " + listId)))
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }
    public List<Task> getAllTasksInList(Long listId) {
        return taskListRepository.findById(listId)
                .map(TaskList::getTasks)
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }

    public List<Task> getTasksFilteredByStatus(Long listId, String status) {
        return taskListRepository.findById(listId)
                .map(taskList -> taskList.getTasks().stream()
                        .filter(task -> task.getStatus().equalsIgnoreCase(status))
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }

    public List<Task> getTasksFilteredByResponsible(Long listId, String responsible) {
        return taskListRepository.findById(listId)
                .map(taskList -> taskList.getTasks().stream()
                        .filter(task -> task.getResponsible().equalsIgnoreCase(responsible))
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }

    public List<Task> getTasksFilteredByPriority(Long listId, int priority) {
        return taskListRepository.findById(listId)
                .map(taskList -> taskList.getTasks().stream()
                        .filter(task -> task.getPriority() == priority)
                        .toList())
                .orElseThrow(() -> new IllegalArgumentException("Task list with id " + listId + " does not exist."));
    }
}
