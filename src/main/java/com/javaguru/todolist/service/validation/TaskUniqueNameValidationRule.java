package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.dto.TaskDto;
import com.javaguru.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskUniqueNameValidationRule implements TaskValidationRule {

    private final TaskRepository repository;

    @Autowired
    public TaskUniqueNameValidationRule(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(TaskDto taskDto) {
        checkNotNull(taskDto);
        if (repository.existsByName(taskDto.getName())) {
            throw new TaskValidationException("Task name must be unique.");
        }
    }
}
