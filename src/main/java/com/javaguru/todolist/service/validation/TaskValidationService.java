package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;

import java.util.HashSet;
import java.util.Set;

public class TaskValidationService {

    private Set<TaskValidationRule> validationRules = new HashSet<>();

    public TaskValidationService() {
        validationRules.add(new TaskNameValidationRule());
    }

    public void validate(Task task) {
        validationRules.forEach(s -> s.validate(task));
    }

}
