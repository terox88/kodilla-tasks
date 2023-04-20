package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }
    public Task getTask (final long id) throws TaskNotFoundException {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }
    public Task saveTask(final Task task) {
        return repository.save(task);
    }
    public void deleteTask(final long id) throws TaskNotFoundException {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new TaskNotFoundException();
        }

    }


}
