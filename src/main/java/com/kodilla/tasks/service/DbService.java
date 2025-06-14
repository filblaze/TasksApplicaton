package com.kodilla.tasks.service;

import com.kodilla.tasks.controller.TaskNotFoundException;
import com.kodilla.tasks.domain.Task;
import com.kodilla.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
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

    public Task findTaskById(Long taskId) {
        return repository.findById(taskId).get();
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public Task getTask(final Long id) throws TaskNotFoundException {
        return repository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public void deleteTask(final Long id) {
        repository.deleteById(id);
    }
}
