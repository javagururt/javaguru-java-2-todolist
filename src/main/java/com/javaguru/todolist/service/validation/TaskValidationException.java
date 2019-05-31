package com.javaguru.todolist.service.validation;

class TaskValidationException extends RuntimeException {

    public TaskValidationException(String message) {
        super(message);
    }
}
