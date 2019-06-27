package com.javaguru.todolist.console.action;

import org.springframework.stereotype.Component;

@Component
public class ExitAction implements Action {

    private static final String ACTION_NAME = "Exit";

    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
