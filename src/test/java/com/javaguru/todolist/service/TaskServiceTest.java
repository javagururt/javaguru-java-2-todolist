package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.repository.TaskInMemoryRepository;
import com.javaguru.todolist.service.validation.TaskValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TaskInMemoryRepository repository;

    @Mock
    private TaskValidationService validationService;

    @InjectMocks
    private TaskService victim;

    @Captor
    private ArgumentCaptor<Task> taskCaptor;

    @Test
    public void shouldCreateTaskSuccessfully() {
        Task task = task();
        when(repository.insert(task)).thenReturn(task);

        Long result = victim.createTask(task);

        verify(validationService).validate(taskCaptor.capture());
        Task captorResult = taskCaptor.getValue();

        assertEquals(captorResult, task);
        assertEquals(task.getId(), result);
    }

    private Task task() {
        Task task = new Task();
        task.setName("TEST_NAME");
        task.setDescription("TEST_DESCRIPTION");
        task.setId(1001L);
        return task;
    }
}