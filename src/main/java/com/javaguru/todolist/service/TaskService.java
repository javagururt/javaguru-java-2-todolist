package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.domain.User;
import com.javaguru.todolist.repository.TaskRepository;
import com.javaguru.todolist.service.validation.TaskValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TaskService {

    private final TaskRepository repository;
    private final TaskValidationService validationService;

    @Autowired
    public TaskService(TaskRepository repository,
                       TaskValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    public Task createTask(Task task) {
        validationService.validate(task);
        return repository.save(task);
    }

    public Task findTaskById(Long id) {
        return repository.findTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found, id: " + id));
    }
}