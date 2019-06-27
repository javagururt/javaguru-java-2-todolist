package com.javaguru.todolist.service.validation;

public class TaskValidationException extends RuntimeException {

    TaskValidationException(String message) {
        super(message);
    }
}
