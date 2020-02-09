package com.crud.tasks2.controller;

import com.crud.tasks2.domain.TaskDto;
import com.crud.tasks2.mapper.TaskMapper;
import com.crud.tasks2.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks()); }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable(value = "taskId") Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new));
    }
//    (value = "taskId") dodane, znalezione: https://salonegupta.wordpress.com/2014/04/01/spring-annotations-and-javac-debug-mode/#more-5

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{tasksId}")
    public void deleteTask(@PathVariable(value = "taskId") Long taskId) throws TaskNotFoundException{
        service.deleteTask(taskId);
    }

}