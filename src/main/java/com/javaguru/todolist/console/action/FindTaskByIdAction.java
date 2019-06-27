package com.javaguru.todolist.console.action;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.service.TaskService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class FindTaskByIdAction implements Action {

    private static final String ACTION_NAME = "Find by ID";

    private final TaskService taskService;

    public FindTaskByIdAction(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id: ");
        Long id = scanner.nextLong();
        Task response = taskService.findTaskById(id);
        System.out.println("Response: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
