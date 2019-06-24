package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.repository.TaskInMemoryRepository;
import com.javaguru.todolist.service.validation.TaskValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskInMemoryRepository repository;

    @Mock
    private TaskValidationService validationService;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;

    private TaskService victim;

    @Before
    public void setUp() throws Exception {
        victim = new TaskService(repository, validationService);
    }

    @Test
    public void shouldCreateTask() {
        Task task = task();
        when(repository.save(task)).thenReturn(task);

        Long result = victim.createTask(task);

        verify(validationService).validate(taskCaptor.capture());
        Task captorResult = taskCaptor.getValue();

        assertThat(captorResult).isEqualTo(task());
        assertThat(task.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindTaskById() {
        when(repository.findTaskById(1001L)).thenReturn(Optional.ofNullable(task()));

        Task result = victim.findTaskById(1001L);

        assertThat(result).isEqualTo(task());
    }

    @Test
    public void shouldThrowExceptionTaskNotFound() {
        when(repository.findTaskById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findTaskById(1001L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task not found, id: 1001");
    }

    private Task task() {
        Task task = new Task();
        task.setName("TEST_NAME");
        task.setDescription("TEST_DESCRIPTION");
        task.setId(1001L);
        return task;
    }
}