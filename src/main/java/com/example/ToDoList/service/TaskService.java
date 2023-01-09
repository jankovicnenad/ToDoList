package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDtoResponse;
import com.example.ToDoList.DTO.TaskDtoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskService {

    public List<TaskDtoResponse> getAllTasks(Long priorityId, Long statusId);

    public TaskDtoResponse findById(Long id);

    public TaskDtoResponse saveImage(TaskDtoResponse taskDtoResponse, MultipartFile multipartFile);

    public TaskDtoResponse save(TaskDtoResponse taskDtoResponse);

    public void deleteById(Long id);

    public void updateTask(TaskDtoRequest taskDto);


}
