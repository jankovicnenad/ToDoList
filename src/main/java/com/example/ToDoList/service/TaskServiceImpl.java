package com.example.ToDoList.service;

import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Image;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;


    private final StatusRepository statusRepository;

    private final PriorityRepository priorityRepository;

    private final ImageService imageService;


    public TaskServiceImpl(TaskRepository task, StatusRepository status, PriorityRepository priority, ImageService imageService)
    {taskRepository = task;
     statusRepository = status;
     priorityRepository = priority;
     this.imageService = imageService;
    }


    public Task convertTaskDtoToTask(TaskDto taskDto){
        Task t = new Task();
        t.setId(taskDto.getId());
        t.setName(taskDto.getName());
        t.setStart_date(taskDto.getStart_date());
        t.setEndDate(taskDto.getEnd_date());
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
        taskDto.setEnd_date(task.getEndDate());
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(task.getPriority().getId());
        priorityDto.setPriority(task.getPriority().getPriority());
        taskDto.setPriority_dto(priorityDto);
        StatusDto statusDto = new StatusDto();
        statusDto.setId(task.getStatus().getId(1));
        statusDto.setStatus_name(task.getStatus().getStatus());
        taskDto.setStatus_dto(statusDto);
        return taskDto;
    }
    @Override
    public List<TaskDto> getAllTasks(Long priorityId, Long statusId) {
        List<Task> tasks = new ArrayList<>();
        if(Objects.nonNull(priorityId)&& Objects.isNull(statusId)){
            tasks = taskRepository.selectTasksByPriority(priorityId);
        }else if (Objects.nonNull(statusId)&& Objects.isNull(priorityId)){
            tasks = taskRepository.selectTaskByStatus(statusId);
        }
        else if (Objects.nonNull(priorityId) && Objects.nonNull(statusId)){
            tasks = taskRepository.selectTasksByPriorityAndStatus(priorityId, statusId);
        }
        else {
            tasks = taskRepository.findAll();
        }
        List <TaskDto> tDto = new ArrayList<>();
        for (Task t : tasks){
            TaskDto taskDto = convertTaskToTaskDto(t);
            tDto.add(taskDto);
        }
        return tDto;
    }

    @Override
    public TaskDto findById(Long id) {
       Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException("Task id not found - " +id));
       TaskDto tDto = convertTaskToTaskDto(task);
        return tDto;
    }

    @Override
    public TaskDto saveImage(TaskDto taskDto, MultipartFile multipartFile){

        Task task = convertTaskDtoToTask(taskDto);
        Optional<Status> status = statusRepository.findById(taskDto.getStatus_dto().getId());
        Optional<Priority> priority = priorityRepository.findById(taskDto.getPriority_dto().getId());

        task.setPriority(priority.get());

        task.setStatus(status.get());
        if(Objects.nonNull(multipartFile)){
        Image image = new Image();
        try {
            String imageUrl = imageService.uploadFile(multipartFile);
            image.setUrl(imageUrl);
            image.setOriginalName(multipartFile.getOriginalFilename());
            image.setModifiedDate(LocalDateTime.now());
            image.setCreatedDate(LocalDateTime.now());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        image.setTask(task);
        task.getImages().add(image);
        }


        taskRepository.save(task);
        return convertTaskToTaskDto(task);

    }

    @Override
    public TaskDto save(TaskDto taskDto) {
        Task task = convertTaskDtoToTask(taskDto);
        Optional<Status> status = statusRepository.findById(taskDto.getStatus_dto().getId());
        Optional<Priority> priority = priorityRepository.findById(taskDto.getPriority_dto().getId());

        task.setPriority(priority.get());

        task.setStatus(status.get());

        taskRepository.save(task);
        return  convertTaskToTaskDto(task);
    }

    @Override
    public void deleteById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        taskRepository.delete(task);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Long id = taskDto.getId();
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Task task1 = convertTaskDtoToTask(taskDto);
        task1.setId(task.getId());
        Status status = statusRepository.findById(taskDto.getStatus_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Priority priority = priorityRepository.findById(taskDto.getPriority_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        task1.setStatus(status);
        task1.setPriority(priority);
        if(status.getStatus()!=null){
        if(status.getStatus().equals("DONE")){
            task1.setEndDate(LocalDateTime.now());
        }}
        taskRepository.save(task1);


    }


}