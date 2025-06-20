package com.kodilla.tasks.repository;

import com.kodilla.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    Optional<Task> findById(Long id);

    @Override
    void deleteById(Long id);
}
