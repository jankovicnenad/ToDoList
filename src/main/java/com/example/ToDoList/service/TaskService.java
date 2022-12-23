package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    public List<TaskDto> getAllTasks();

    public TaskDto findById(int id);

    public TaskDto saveImage(TaskDto taskDto, MultipartFile multipartFile);

    public TaskDto save(TaskDto taskDto);

    public void deleteById(int id);

    public void updateTask(int id, TaskDto taskDto);


}
