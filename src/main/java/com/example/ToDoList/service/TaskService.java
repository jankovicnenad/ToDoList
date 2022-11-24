package com.example.ToDoList.service;

import com.example.ToDoList.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public List<Task> findAll();

    public Optional<Task> findById(int id);

    public void save(Task task);

    public void deleteById(int id);

}
