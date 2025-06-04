package com.kodilla.tasks.service;

import com.kodilla.tasks.domain.Task;
import com.kodilla.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task findTaskById(Long taskId) {
        return repository.findById(taskId).orElseThrow();
    }
}
