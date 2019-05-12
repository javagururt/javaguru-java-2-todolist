package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskNameValidationRuleTest {

    @Spy
    private TaskNameValidationRule victim;

    private Task input;

    @Test
    public void shouldThrowTaskValidationException() {
        input = task(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(TaskValidationException.class)
                .hasMessage("Task name must be not null.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = task("valid name");

        victim.validate(input);

        verify(victim).checkNotNull(input);
    }

    private Task task(String name) {
        Task task = new Task();
        task.setName(name);
        return task;
    }

}