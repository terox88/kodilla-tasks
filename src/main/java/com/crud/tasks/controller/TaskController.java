package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final DbService service;
    private final TaskMapper taskMapper;
@GetMapping
    public List<TaskDto> getTasks() {
    List<Task> tasks = service.getAllTasks();
    return taskMapper.mapToTaskDtoList(tasks);

    }
@GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
    return taskMapper.mapToTaskDto(service.findById(taskId));
    }
@DeleteMapping
    public void deleteTask(Long taskId) {

    }
@PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "Edited test title", "Test content");
    }
@PostMapping
    public void createTask(TaskDto taskDto) {

    }
}
