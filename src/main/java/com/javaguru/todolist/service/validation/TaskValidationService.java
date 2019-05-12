package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;

import java.util.Set;

public class TaskValidationService {

    private final Set<TaskValidationRule> validationRules;

    public TaskValidationService(Set<TaskValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Task task) {
        validationRules.forEach(s -> s.validate(task));
    }

}
