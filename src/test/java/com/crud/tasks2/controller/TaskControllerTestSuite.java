package com.crud.tasks2.controller;

import com.crud.tasks2.domain.Task;
import com.crud.tasks2.domain.TaskDto;
import com.crud.tasks2.mapper.TaskMapper;
import com.crud.tasks2.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService dbService;

    @Test
    public void shouldFetchTasks() throws Exception {
        //Given
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(new TaskDto(101L, "test task 1", "test content 1"));
        taskDtos.add(new TaskDto(102L, "test task 2", "test content 2"));

        when(taskController.getTasks()).thenReturn(taskDtos);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].id", is(102)))
                .andExpect(jsonPath("$[0].title", is("test task 1")))
                .andExpect(jsonPath("$[1].content", is("test content 2")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Long taskId = 101L;
        TaskDto taskDto =  new TaskDto(taskId, "test task 1", "test content 1");

        when(taskController.getTask(taskId)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask")
                .param("id", "101L")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(101L)))
                .andExpect(jsonPath("$.title", is("test task 1")))
                .andExpect(jsonPath("$.content", is("test content 1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {

        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask")
                .param("id", "101L")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        //Given
        TaskDto taskDto1 =  new TaskDto(101L, "test task 1", "test content 1");
        TaskDto taskDto2 =  new TaskDto(102L, "test task 2", "test content 2");

        when(taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto1)))).thenReturn(taskDto2);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto1);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(102)))
                .andExpect(jsonPath("$.title", is("test task 2")))
                .andExpect(jsonPath("$.content", is("test content 2")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        //Given
        Task task = new Task(101L, "test task 1", "test content 1");
        TaskDto taskDto =  new TaskDto(101L, "test task 1", "test content 1");

        when(dbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When and Then
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }



}
