package com.javaguru.todolist.console;

import com.javaguru.todolist.console.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class ConsoleUIConfiguration {

    private final Action createTaskAction;
    private final Action findTaskByIdAction;
    private final Action exitAction;

    @Autowired
    public ConsoleUIConfiguration(Action createTaskAction,
                                  Action findTaskByIdAction,
                                  Action exitAction) {
        this.createTaskAction = createTaskAction;
        this.findTaskByIdAction = findTaskByIdAction;
        this.exitAction = exitAction;
    }


    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(createTaskAction);
        actions.add(findTaskByIdAction);
        actions.add(exitAction);
        return new ConsoleUI(actions);
    }
}
