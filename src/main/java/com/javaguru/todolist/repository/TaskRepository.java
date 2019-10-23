package com.javaguru.todolist.repository;

import com.javaguru.todolist.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    Long save(Task task);

    void update(Task task);

    Optional<Task> findTaskById(Long id);

    boolean existsByName(String name);

    Optional<Task> findTaskByName(String name);

    List<Task> findAll();

    void delete(Task task);

}