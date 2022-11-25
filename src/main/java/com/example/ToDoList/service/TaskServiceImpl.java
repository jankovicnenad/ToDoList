package com.example.ToDoList.service;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository task)
    {taskRepository = task;}

    public Task convertTaskToDto(TaskDto taskDto){
        Task t = new Task();
        t.setId(taskDto.getId());
        t.setName(taskDto.getName());
        t.setStartDate(taskDto.getStartDate());
        return t;
    }

    public TaskDto convertDtoToTask(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStartDate(task.getStartDate());
        return taskDto;
    }
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public void save(TaskDto taskDto) {
        Task task = new Task();
        Priority priority = new Priority();
        Status status = new Status();
        task.setId(status.getId());
        task.setId(priority.getId());
        task = convertTaskToDto(taskDto);
        taskRepository.save(task);
    }
    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}