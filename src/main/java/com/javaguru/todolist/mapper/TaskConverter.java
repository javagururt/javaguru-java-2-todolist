package com.javaguru.todolist.mapper;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.dto.TaskDto;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {

    public Task convert(TaskDto taskDto) {
        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setName(taskDto.getName());
        task.setId(taskDto.getId());
        return task;
    }

    public TaskDto convert(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(task.getDescription());
        taskDto.setName(task.getName());
        taskDto.setId(task.getId());
        return taskDto;
    }
}
