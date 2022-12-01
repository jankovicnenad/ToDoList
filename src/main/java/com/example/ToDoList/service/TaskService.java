package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;

import java.util.List;

public interface TaskService {

    public List<TaskDto> getAllTasks();

    public TaskDto findById(int id);

    public TaskDto save(TaskDto taskDto);

    public void deleteById(int id);

}
