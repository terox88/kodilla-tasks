package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();

   Optional<Task> findById(long id);
    @Override
    Task save(Task task);


    void deleteById(long id);

}
