package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.repository.TaskInMemoryRepository;

public class TaskUniqueNameValidationRule implements TaskValidationRule {

    private final TaskInMemoryRepository repository;

    public TaskUniqueNameValidationRule(TaskInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Task task) {
        checkNotNull(task);
        if (repository.existsByName(task.getName())) {
            throw new TaskValidationException("Task name must be unique.");
        }
    }
}
