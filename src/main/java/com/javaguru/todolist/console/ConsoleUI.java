package com.javaguru.todolist.console;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleUI {

    private final TaskService taskService;

    @Autowired
    public ConsoleUI(TaskService taskService) {
        this.taskService = taskService;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create task");
                System.out.println("2. Find task by id");
                System.out.println("3. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        createTask();
                        break;
                    case 2:
                        findTask();
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }

    private void createTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task name: ");
        String name = scanner.nextLine();
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task();
        task.setName(name);
        task.setDescription(description);

        Long id = taskService.createTask(task);
        System.out.println("Result: " + id);
    }

    private void findTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task id: ");
        Long id = scanner.nextLong();
        Task task = taskService.findTaskById(id);
        System.out.println(task);
    }

}
