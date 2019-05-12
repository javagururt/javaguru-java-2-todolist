package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TaskInMemoryRepositoryTest {

    private static final String TASK_NAME = "TEST_NAME";
    private static final String TASK_DESCRIPTION = "TEST_DESCRIPTION";
    private static final long TASK_ID = 0L;

    private TaskInMemoryRepository victim = new TaskInMemoryRepository();

    private Task task = task();

    @Test
    public void shouldInsert() {
        Task result = victim.save(task);

        assertThat(result).isEqualTo(expectedTask());
    }

    @Test
    public void shouldFindById() {
        victim.save(task);

        Optional<Task> result = victim.findTaskById(TASK_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedTask());
    }

    @Test
    public void shouldFindByName() {
        victim.save(task);

        Optional<Task> result = victim.findTaskByName(TASK_NAME);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedTask());
    }

    @Test
    public void shouldExistByName() {
        victim.save(task);

        boolean result = victim.existsByName(TASK_NAME);
        assertThat(result).isTrue();
    }

    private Task expectedTask() {
        Task task = new Task();
        task.setName(TASK_NAME);
        task.setDescription(TASK_DESCRIPTION);
        task.setId(TASK_ID);
        return task;
    }

    private Task task() {
        Task task = new Task();
        task.setName(TASK_NAME);
        task.setDescription(TASK_DESCRIPTION);
        return task;
    }
}