package com.example.ToDoList.service;

import com.example.ToDoList.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskService {

    public List<Task> findAll();

    public List<Task> findById(int id);

    public void save(Task task);

    public void deleteById(int id);

}
