package com.javaguru.todolist.service.validation;

class TaskValidationException extends RuntimeException {

    TaskValidationException(String message) {
        super(message);
    }
}
