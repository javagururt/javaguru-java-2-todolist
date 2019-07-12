package com.javaguru.todolist.console.action;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.service.TaskService;
import com.javaguru.todolist.service.validation.TaskValidationException;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateTaskAction implements Action {

    private static final String ACTION_NAME = "Create Task";

    private final TaskService taskService;

    public CreateTaskAction(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task name:");
        String name = scanner.nextLine();
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        try {
            Task response = taskService.createTask(task);
            System.out.println("Response: " + response);
        } catch (TaskValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
