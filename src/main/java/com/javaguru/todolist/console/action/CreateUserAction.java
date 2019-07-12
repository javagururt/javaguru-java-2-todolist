package com.javaguru.todolist.console.action;

import com.javaguru.todolist.domain.User;
import com.javaguru.todolist.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CreateUserAction implements Action {

    private static final String ACTION_NAME = "Create user";

    private final UserService userService;

    public CreateUserAction(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user login:");
        String name = scanner.nextLine();
        System.out.println("Enter user password: ");
        String description = scanner.nextLine();

        User user = new User();
        user.setLogin(name);
        user.setPassword(description);

        Long response = userService.createUser(user);
        System.out.println("Response: " + response);
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
