package com.javaguru.todolist.controller;

import com.javaguru.todolist.dto.TaskDto;
import com.javaguru.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Validated({TaskDto.Create.class}) @RequestBody TaskDto taskDto,
                                       UriComponentsBuilder builder) {
        Long id = taskService.createTask(taskDto);
        return ResponseEntity.created(builder.path("/tasks/{id}").buildAndExpand(id).toUri()).build();
    }


    @PutMapping("/{id}/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") Long id, @PathVariable("userId") Long userId, @Validated({TaskDto.Update.class}) @RequestBody TaskDto taskDto) {
        taskService.updateTask(taskDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/{id}")
    public TaskDto findTaskById(@PathVariable("id") Long id) {
        return taskService.findTaskById(id);
    }

    @GetMapping(params = "name")
    public TaskDto findTaskByName(@RequestParam("name") String name) {
        return taskService.findTaskByName(name);
    }

    @GetMapping
    public List<TaskDto> findAll() {
        return taskService.findAll();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNoSuchElementException(NoSuchElementException ex) {

    }

}
