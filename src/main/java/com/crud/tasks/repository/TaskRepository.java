package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();
    Task findById(long id);
}
