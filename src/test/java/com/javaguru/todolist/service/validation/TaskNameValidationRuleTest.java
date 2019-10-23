package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.dto.TaskDto;
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

    private TaskDto input;

    @Test
    public void shouldThrowTaskValidationException() {
        input = taskDto(null);

        assertThatThrownBy(() -> victim.validate(input))
                .isInstanceOf(TaskValidationException.class)
                .hasMessage("Task name must be not null.");
        verify(victim).checkNotNull(input);
    }

    @Test
    public void shouldValidateSuccess() {
        input = taskDto("valid name");

        victim.validate(input);

        verify(victim).checkNotNull(input);
    }

    private TaskDto taskDto(String name) {
        TaskDto taskDto = new TaskDto();
        taskDto.setName(name);
        return taskDto;
    }

}