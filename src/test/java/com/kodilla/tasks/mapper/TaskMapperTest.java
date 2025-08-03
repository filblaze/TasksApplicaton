package com.kodilla.tasks.mapper;

import com.kodilla.tasks.domain.Task;
import com.kodilla.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "testTask", "This is a test task");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(1L, task.getId());
        assertEquals("testTask", task.getTitle());
        assertEquals("This is a test task", task.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "testTask", "This is a test task");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, taskDto.getId());
        assertEquals("testTask", taskDto.getTitle());
        assertEquals("This is a test task", taskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        List<Task> taskList = List.of(new Task(1L, "testTask", "This is a test task"));
        //When
        List<TaskDto> taskListDto = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertNotNull(taskListDto);
        assertEquals(1, taskListDto.size());
        assertEquals(1L, taskListDto.get(0).getId());
        assertEquals("testTask", taskListDto.get(0).getTitle());
        assertEquals("This is a test task", taskListDto.get(0).getContent());
    }
}