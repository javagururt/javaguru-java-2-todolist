package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@Profile("inmemorydb")
public class TaskInMemoryRepository implements TaskRepository {

    private Long taskIdSequence = 0L;
    private Map<Long, Task> tasks = new HashMap<>();

    public Task save(Task task) {
        task.setId(taskIdSequence++);
        tasks.put(task.getId(), task);
        return task;
    }

    public Optional<Task> findTaskById(Long id) {
        return Optional.ofNullable(tasks.get(id));
    }

    public boolean existsByName(String name) {
        return tasks.values().stream()
                .anyMatch(task -> task.getName().equalsIgnoreCase(name));
    }

    public Optional<Task> findTaskByName(String name) {
        return tasks.values().stream()
                .filter(task -> task.getName().equalsIgnoreCase(name))
                .findFirst();
    }

}
