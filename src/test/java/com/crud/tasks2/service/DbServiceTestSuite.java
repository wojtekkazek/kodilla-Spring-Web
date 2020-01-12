package com.crud.tasks2.service;

import com.crud.tasks2.domain.Task;
import com.crud.tasks2.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void shouldGetTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title", "content"));

        when(taskRepository.findAll()).thenReturn(tasks);

        //When
        List<Task> result = dbService.getAllTasks();

        //Then
        Assert.assertEquals(result.size(),1);
    }

    @Test
    public void shouldSaveTask() {
        //Given
        Task task = new Task(1L, "title", "content");

        when(taskRepository.save(any())).thenReturn(task);

        //When
        Task result = dbService.saveTask(task);

        //Then
        Assert.assertEquals(result.getTitle(),"title");
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "title", "content");
        Optional<Task> optionalTask = Optional.of(task);

        when(taskRepository.findById(any())).thenReturn(optionalTask);

        //When
        Optional<Task> result = dbService.getTask(1L);

        //Then
        Assert.assertEquals(result.get().getTitle(),"title");
    }
}
