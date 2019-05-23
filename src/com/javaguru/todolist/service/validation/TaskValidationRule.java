package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;

public interface TaskValidationRule {

    void validate(Task task);

    default void checkNotNull(Task task) {
        if (task == null) {
            throw new TaskValidationException("Task must be not null");
        }
    }
}
