package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.dto.TaskDto;
import com.javaguru.todolist.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskUniqueNameValidationRuleTest {

    @Mock
    private TaskRepository hibernateTaskRepository;

    @Spy
    @InjectMocks
    private TaskUniqueNameValidationRule victim;

    private TaskDto taskDto = taskDto();

    @Test
    public void shouldThrowException() {
        when(hibernateTaskRepository.existsByName(taskDto.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(taskDto))
                .isInstanceOf(TaskValidationException.class)
                .hasMessage("Task name must be unique.");

        verify(victim).checkNotNull(taskDto);
    }

    @Test
    public void shouldValidateSuccess() {
        when(hibernateTaskRepository.existsByName(taskDto.getName()))
                .thenReturn(false);

        victim.validate(taskDto);

        verify(victim).checkNotNull(taskDto);
    }

    private TaskDto taskDto() {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(123L);
        taskDto.setDescription("TEST_DESCRIPTION");
        taskDto.setName("TEST_NAME");
        return taskDto;
    }
}