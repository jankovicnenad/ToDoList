package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDto;

import java.util.List;

public interface TaskService {

    public List<TaskDto> getAllTasks();

    public List<TaskDto> findById(int id);

    public TaskDto save(TaskDto taskDto);

    public void deleteById(int id);

}
