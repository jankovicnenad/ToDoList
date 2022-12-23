package com.example.ToDoList.service;

import com.example.ToDoList.DTO.TaskDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TaskService {

    public List<TaskDto> getAllTasks(Long priorityId, Long statusId);

    public TaskDto findById(Long id);

    public TaskDto saveImage(TaskDto taskDto, MultipartFile multipartFile);

    public TaskDto save(TaskDto taskDto);

    public void deleteById(Long id);

    public void updateTask(Long id, TaskDto taskDto);


}
