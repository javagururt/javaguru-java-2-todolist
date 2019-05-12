package com.javaguru.todolist.service.validation;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.repository.TaskInMemoryRepository;
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
    private TaskInMemoryRepository taskInMemoryRepository;

    @Spy
    @InjectMocks
    private TaskUniqueNameValidationRule victim;

    private Task task = task();

    @Test
    public void shouldThrowException() {
        when(taskInMemoryRepository.existsByName(task.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(task))
                .isInstanceOf(TaskValidationException.class)
                .hasMessage("Task name must be unique.");

        verify(victim).checkNotNull(task);
    }

    @Test
    public void shouldValidateSuccess() {
        when(taskInMemoryRepository.existsByName(task.getName()))
                .thenReturn(false);

        victim.validate(task);

        verify(victim).checkNotNull(task);
    }

    private Task task() {
        Task task = new Task();
        task.setId(123L);
        task.setDescription("TEST_DESCRIPTION");
        task.setName("TEST_NAME");
        return task;
    }
}