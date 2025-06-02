package com.kodilla.tasks.controller;

import com.kodilla.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        return new TaskDto(1L, "test title", "test_content");
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {

    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {
        return new TaskDto(1L, "edited task", "TEST_CONTENT");
    }

    @PostMapping
    public void createTask(TaskDto taskDto) {

    }
}
