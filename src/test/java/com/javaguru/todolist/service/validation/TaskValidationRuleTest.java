package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(MockitoJUnitRunner.class)
public class TaskValidationRuleTest {

    @Spy
    private TaskValidationRule victim;

    @Test
    public void shouldThrowValidationException() {
        assertThatThrownBy(() -> victim.checkNotNull(null))
                .isInstanceOf(TaskValidationException.class)
                .hasMessage("Task must be not null");
    }

    @Test
    public void shouldCheckNotNull() {
        Task task = new Task();

        victim.checkNotNull(task);
    }

}