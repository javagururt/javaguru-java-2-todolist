package com.javaguru.todolist.console;

import com.javaguru.todolist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class ConsoleUIConfiguration {

    @Autowired
    private Action createTaskAction;

    @Autowired
    private Action findTaskByIdAction;

    @Autowired
    private Action exitAction;

    @Autowired
    private Action createUserAction;

    @Autowired
    private Action assignTaskAction;

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createTaskAction);
        actions.add(findTaskByIdAction);
        actions.add(createUserAction);
        actions.add(assignTaskAction);
        actions.add(exitAction);
        return new ConsoleUI(actions);
    }

}
