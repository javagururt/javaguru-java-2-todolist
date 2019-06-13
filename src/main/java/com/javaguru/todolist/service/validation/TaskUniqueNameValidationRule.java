package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.repository.TaskInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskUniqueNameValidationRule implements TaskValidationRule {

    private final TaskInMemoryRepository repository;

    @Autowired
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
