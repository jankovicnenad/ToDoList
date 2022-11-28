package com.example.ToDoList.service;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import org.springframework.stereotype.Service;

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
        t.setStart_date(taskDto.getStartDate());
        return t;
    }

    public TaskDto convertDtoToTask(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStartDate(task.getStart_date());
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
        int id=1;
        Priority priority = new Priority();
        Status status = new Status();
        task.setStatus(status);
        task.setPriority(priority);
        task = convertTaskToDto(taskDto);
        taskRepository.save(task);
    }
    @Override
    public void deleteById(int id) {
        taskRepository.deleteById(id);
    }

}