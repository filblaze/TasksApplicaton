package com.kodilla.tasks.controller;

import com.google.gson.Gson;
import com.kodilla.tasks.domain.Task;
import com.kodilla.tasks.domain.TaskDto;
import com.kodilla.tasks.mapper.TaskMapper;
import com.kodilla.tasks.service.DbService;
import com.kodilla.tasks.trello.facade.TrelloFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper mapper;

    @Test
    void shouldGetNoTasks() throws Exception {
        //Given
        when(service.getAllTasks()).thenReturn(List.of());
        when(mapper.mapToTaskDtoList(any(List.class))).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }
    @Test
    void shouldGetTasks() throws Exception {
        //Given
        List<Task> taskList = List.of(new Task(1L, "test task", "this is a test task"));
        List<TaskDto> taskDtoList = List.of(new TaskDto(1L, "test task", "this is a test task"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(mapper.mapToTaskDtoList(any(List.class))).thenReturn(taskDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("this is a test task")));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "this is a test task");
        TaskDto taskDto = new TaskDto(1L, "test task", "this is a test task");

        when(service.getTask(any())).thenReturn(task);
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("this is a test task")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        //Given
        doNothing().when(service).deleteTask(any(Long.class));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "this is a test task");
        Task savedTask = new Task(1L, "test task", "this is a test task");
        TaskDto taskDto = new TaskDto(1L, "test task", "this is a test task");

        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(service.saveTask(any(Task.class))).thenReturn(savedTask);
        Gson gson = new Gson();
        String jsonTask = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonTask))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("this is a test task")));

    }

    @Test
    void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(1L, "test task", "this is a test task");
        Task savedTask = new Task(1L, "test task", "this is a test task");
        TaskDto taskDto = new TaskDto(1L, "test task", "this is a test task");

        when(mapper.mapToTask(any(TaskDto.class))).thenReturn(task);
        when(mapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        when(service.saveTask(any(Task.class))).thenReturn(savedTask);
        Gson gson = new Gson();
        String jsonTask = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonTask))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("test task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("this is a test task")));
    }
}
