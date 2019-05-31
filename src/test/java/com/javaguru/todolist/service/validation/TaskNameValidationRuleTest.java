package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TaskNameValidationRuleTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private TaskNameValidationRule victim = new TaskNameValidationRule();

    private Task input;

    @Test
    public void shouldThrowTaskValidationException() {
        input = task(null);

        expectedException.expect(TaskValidationException.class);
        expectedException.expectMessage("Task name must be not null.");
        victim.validate(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = task("valid name");

        victim.validate(input);
    }

    private Task task(String name) {
        Task task = new Task();
        task.setName(name);
        return task;
    }

}