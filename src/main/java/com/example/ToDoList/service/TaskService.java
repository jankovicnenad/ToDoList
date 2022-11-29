package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public List<Task> findAll();

    public Optional<Task> findById(int id);

    public TaskDto save(TaskDto taskDto);

    public void deleteById(int id);

}
