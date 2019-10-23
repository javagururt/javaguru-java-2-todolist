package com.javaguru.todolist.controller;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.dto.TaskDTO;
import com.javaguru.todolist.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody TaskDTO taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        taskService.createTask(task);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/{id}")
    public TaskDTO findTaskById(@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        return new TaskDTO(task.getId(), task.getName(), task.getDescription());
    }
}
