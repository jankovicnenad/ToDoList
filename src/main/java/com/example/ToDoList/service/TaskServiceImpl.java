package com.example.ToDoList.service;

import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;


    private StatusRepository statusRepository;

    private PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository task, StatusRepository status, PriorityRepository priority)
    {taskRepository = task;
     statusRepository = status;
     priorityRepository = priority;
    }


    public Task convertTaskDtoToTask(TaskDto taskDto){
        Task t = new Task();
        t.setId(taskDto.getId());
        t.setName(taskDto.getName());
        t.setStart_date(taskDto.getStart_date());
        Status status = new Status();
        status.setId(taskDto.getStatus_dto().getId());
        Priority priority = new Priority();
        priority.setId(taskDto.getPriority_dto().getId());
        t.setStatus(status);
        t.setPriority(priority);
        return t;
    }

    public TaskDto convertTaskToTaskDto(Task task){
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStart_date(task.getStart_date());
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(task.getPriority().getId());
        priorityDto.setPriority(task.getPriority().getPriority());
        taskDto.setPriority_dto(priorityDto);
        StatusDto statusDto = new StatusDto();
        statusDto.setId(task.getStatus().getId());
        statusDto.setStatus_name(task.getStatus().getStatus());
        taskDto.setStatus_dto(statusDto);
        return taskDto;
    }
    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List <TaskDto> tDto = new ArrayList<>();
        for (Task t : tasks){
            TaskDto taskDto = convertTaskToTaskDto(t);
            tDto.add(taskDto);
        }
        return tDto;
    }

    @Override
    public TaskDto findById(int id) {
       Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException("Task id not found - " +id));
       TaskDto tDto = convertTaskToTaskDto(task);
        return tDto;
    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task task = convertTaskDtoToTask(taskDto);
        Optional<Status> status = statusRepository.findById(taskDto.getStatus_dto().getId());

        Optional<Priority> priority = priorityRepository.findById(taskDto.getPriority_dto().getId());

        task.setPriority(priority.get());

        task.setStatus(status.get());

        taskRepository.save(task);
        return convertTaskToTaskDto(task);

    }

    @Override
    public void deleteById(int id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        taskRepository.delete(task);
    }

    @Override
    public void updateTask(int id, TaskDto taskDto) {
       Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Task task1 = convertTaskDtoToTask(taskDto);
        task1.setId(task.getId());
        Status status = statusRepository.findById(taskDto.getStatus_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Priority priority = priorityRepository.findById(taskDto.getPriority_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        task1.setStatus(status);
        task1.setPriority(priority);
        if(status.getStatus()!=null){
        if(status.getStatus().equals("DONE")){
            task1.setEndDate(Timestamp.valueOf(LocalDateTime.now()));
        }}
        taskRepository.save(task1);


    }


}