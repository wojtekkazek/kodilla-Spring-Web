package com.crud.tasks2.mapper;

import com.crud.tasks2.domain.Task;
import com.crud.tasks2.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTestSuite {

    private TaskMapper taskMapper = new TaskMapper();

    @Test
    public void shouldMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        Long long1 = 1L;
        Assert.assertEquals(long1, task.getId());
        Assert.assertEquals("title", task.getTitle());
        Assert.assertEquals("content", task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        //Given
        Task task = new Task(1L, "title", "content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        Long long1 = 1L;
        Assert.assertEquals(long1, taskDto.getId());
        Assert.assertEquals("title", taskDto.getTitle());
        Assert.assertEquals("content", taskDto.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "title", "content"));
        tasks.add(new Task(2L, "title", "content"));

        //When
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(tasks);

        //Then
        Long long2 = 2L;
        Assert.assertEquals(long2, taskDtos.get(1).getId());
        Assert.assertEquals("title", taskDtos.get(1).getTitle());
        Assert.assertEquals("content", taskDtos.get(1).getContent());
    }


}
