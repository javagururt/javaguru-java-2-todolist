package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;

import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> findTaskById(Long id);

    boolean existsByName(String name);

    Optional<Task> findTaskByName(String name);
}
