package com.javaguru.todolist;

import com.javaguru.todolist.console.ConsoleUI;
import com.javaguru.todolist.repository.TaskInMemoryRepository;
import com.javaguru.todolist.service.TaskService;
import com.javaguru.todolist.service.validation.TaskNameValidationRule;
import com.javaguru.todolist.service.validation.TaskUniqueNameValidationRule;
import com.javaguru.todolist.service.validation.TaskValidationRule;
import com.javaguru.todolist.service.validation.TaskValidationService;

import java.util.HashSet;
import java.util.Set;

public class ToDoListApplication {

    public static void main(String[] args) {
        TaskInMemoryRepository repository = new TaskInMemoryRepository();

        TaskValidationRule taskNameValidationRule = new TaskNameValidationRule();
        TaskValidationRule taskUniqueNameValidationRule = new TaskUniqueNameValidationRule(repository);
        Set<TaskValidationRule> rules = new HashSet<>();
        rules.add(taskNameValidationRule);
        rules.add(taskUniqueNameValidationRule);

        TaskValidationService validationService = new TaskValidationService(rules);

        TaskService taskService = new TaskService(repository, validationService);

        ConsoleUI consoleUI = new ConsoleUI(taskService);
        consoleUI.execute();
    }
}
