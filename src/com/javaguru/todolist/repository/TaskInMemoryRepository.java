package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskInMemoryRepository {

    private Long taskIdSequence = 0L;
    private Map<Long, Task> tasks = new HashMap<>();

    public Task insert(Task task) {
        task.setId(taskIdSequence++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task findTaskById(Long id) {
        return tasks.get(id);
    }
}
