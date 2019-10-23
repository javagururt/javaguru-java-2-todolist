package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.dto.TaskDto;
import com.javaguru.todolist.mapper.TaskConverter;
import com.javaguru.todolist.repository.TaskRepository;
import com.javaguru.todolist.service.validation.TaskValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private TaskValidationService validationService;

    @Mock
    private TaskConverter taskConverter;

    @Captor
    private ArgumentCaptor<TaskDto> taskCaptor;

    private TaskService victim;

    @Before
    public void setUp() throws Exception {
        victim = new TaskService(repository, validationService, taskConverter);
    }

    @Test
    public void shouldCreateTask() {
        TaskDto taskDto = taskDto();
        Task task = task();
        when(taskConverter.convert(taskDto)).thenReturn(task);
        when(repository.save(task)).thenReturn(task.getId());

        Long result = victim.createTask(taskDto);

        verify(validationService).validate(taskCaptor.capture());
        TaskDto captorResult = taskCaptor.getValue();

        assertThat(captorResult).isEqualTo(taskDto);
        assertThat(task.getId()).isEqualTo(result);
    }

    @Test
    public void shouldFindTaskById() {
        when(repository.findTaskById(1001L)).thenReturn(Optional.ofNullable(task()));
        when(taskConverter.convert(task())).thenReturn(taskDto());

        TaskDto result = victim.findTaskById(1001L);

        assertThat(result).isEqualTo(taskDto());
    }

    @Test
    public void shouldThrowExceptionTaskNotFound() {
        when(repository.findTaskById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findTaskById(1001L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Task not found, id: 1001");
    }

    private TaskDto taskDto() {
        TaskDto task = new TaskDto();
        task.setName("TEST_NAME");
        task.setDescription("TEST_DESCRIPTION");
        task.setId(1001L);
        return task;
    }

    private Task task() {
        Task task = new Task();
        task.setName("TEST_NAME");
        task.setDescription("TEST_DESCRIPTION");
        task.setId(1001L);
        return task;
    }
}