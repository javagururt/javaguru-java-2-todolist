package com.javaguru.todolist.console.action;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.domain.User;
import com.javaguru.todolist.service.TaskService;
import com.javaguru.todolist.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

@Component
class AssignTaskAction implements Action {

    private static final String ACTION_NAME = "Assign task to user";

    private final TaskService taskService;
    private final UserService userService;

    AssignTaskAction(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user id:");
        Long userId = scanner.nextLong();
        System.out.println("Enter task id: ");
        Long taskId = scanner.nextLong();

        Task task = taskService.findTaskById(taskId);
        userService.addTaskToUser(task, userId);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
