package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.dto.TaskDto;
import com.javaguru.todolist.mapper.TaskConverter;
import com.javaguru.todolist.repository.TaskRepository;
import com.javaguru.todolist.service.validation.TaskValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskValidationService validationService;
    private final TaskConverter taskConverter;

    @Autowired
    public TaskService(TaskRepository repository,
                       TaskValidationService validationService, TaskConverter taskConverter) {
        this.repository = repository;
        this.validationService = validationService;
        this.taskConverter = taskConverter;
    }

    public Long createTask(TaskDto taskDto) {
        validationService.validate(taskDto);
        Task task = taskConverter.convert(taskDto);
        return repository.save(task);
    }

    public TaskDto findTaskById(Long id) {
        return repository.findTaskById(id)
                .map(taskConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Task not found, id: " + id));
    }

    public TaskDto findTaskByName(String name) {
        return repository.findTaskByName(name)
                .map(taskConverter::convert)
                .orElseThrow(() -> new NoSuchElementException("Task not found, name: " + name));
    }

    public void updateTask(TaskDto taskDto) {
        Task task = taskConverter.convert(taskDto);
        repository.update(task);
    }

    public List<TaskDto> findAll() {
        return repository.findAll().stream()
                .map(task -> taskConverter.convert(task))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteTask(Long id) {
        repository.findTaskById(id)
                .ifPresent(repository::delete);
    }


}