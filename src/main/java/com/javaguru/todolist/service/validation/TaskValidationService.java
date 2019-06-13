package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TaskValidationService {

    private final Set<TaskValidationRule> validationRules;

    public TaskValidationService(Set<TaskValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(Task task) {
        validationRules.forEach(s -> s.validate(task));
    }

}
