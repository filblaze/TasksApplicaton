package com.kodilla.tasks.controller;

import com.kodilla.tasks.domain.Task;
import com.kodilla.tasks.domain.TaskDto;
import com.kodilla.tasks.mapper.TaskMapper;
import com.kodilla.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final DbService service;
    private final TaskMapper mapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return mapper.mapToTaskDtoList(tasks);
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {
        Task task = service.findTaskById(taskId);
        return mapper.mapToTaskDto(task);
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
