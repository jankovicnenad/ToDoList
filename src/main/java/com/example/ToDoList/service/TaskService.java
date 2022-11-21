package com.example.ToDoList.service;

import com.example.ToDoList.entity.Task;

import java.util.List;

public interface TaskService {

    public List<Task> finfAll();

    public void save(Task task);

    public void deleteById(int id);

}
