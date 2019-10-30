package com.crud.tasks2.service;

import com.crud.tasks2.domain.Task;
import com.crud.tasks2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        getAllTasks().stream()
            .filter (t -> t.getId() == id)
                .collect(Collectors.toList());
        return getAllTasks().get(0);
    }

}
