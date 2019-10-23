package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.dto.TaskDto;
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
        TaskDto taskDto = new TaskDto();

        victim.checkNotNull(taskDto);
    }

}