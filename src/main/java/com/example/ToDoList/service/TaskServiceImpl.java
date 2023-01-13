package com.example.ToDoList.service;

import com.example.ToDoList.DAO.ImageRepository;
import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDtoResponse;
import com.example.ToDoList.DTO.TaskDtoRequest;
import com.example.ToDoList.entity.Image;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    private final StatusRepository statusRepository;

    private final PriorityRepository priorityRepository;

    private final ImageService imageService;

    private final MapperDto mapperDto;
    private final ImageRepository imageRepository;


    public TaskServiceImpl(TaskRepository task, StatusRepository status, PriorityRepository priority, ImageService imageService, MapperDto mapperDto, ImageRepository imageRepository) {
        taskRepository = task;
        statusRepository = status;
        priorityRepository = priority;
        this.imageService = imageService;
        this.mapperDto = mapperDto;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<TaskDtoResponse> getAllTasks(Long priorityId, Long statusId) {
        List<Task> tasks = new ArrayList<>();
        if (Objects.nonNull(priorityId) && Objects.isNull(statusId)) {
            tasks = taskRepository.selectTasksByPriority(priorityId);
        } else if (Objects.nonNull(statusId) && Objects.isNull(priorityId)) {
            tasks = taskRepository.selectTaskByStatus(statusId);
        } else if (Objects.nonNull(priorityId) && Objects.nonNull(statusId)) {
            tasks = taskRepository.selectTasksByPriorityAndStatus(priorityId, statusId);
        } else {
            tasks = taskRepository.findAll();
        }
        List<TaskDtoResponse> tDto = new ArrayList<>();
        for (Task t : tasks) {
            TaskDtoResponse taskDtoResponse = mapperDto.convertTaskToTaskDtoResponse(t);
            tDto.add(taskDtoResponse);
        }
        return tDto;
    }

    @Override
    public TaskDtoResponse findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id not found - " + id));
        TaskDtoResponse tDto = mapperDto.convertTaskToTaskDtoResponse(task);
        return tDto;
    }


    public TaskDtoResponse saveTask(TaskDtoRequest taskDtoRequest, MultipartFile multipartFile) {
        Task task = mapperDto.convertTaskDtoRequestToTask(taskDtoRequest);
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(taskDtoRequest.getStatus_id()).orElseThrow(() -> new NotFoundException("Status id not found - " + taskDtoRequest.getStatus_id())));
        ;
        Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(taskDtoRequest.getPriority_id()).orElseThrow(() -> new NotFoundException("Priority id not found - " + taskDtoRequest.getPriority_id())));

        task.setPriority(priority.get());

        task.setStatus(status.get());
        if (Objects.nonNull(multipartFile)) {
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
        return mapperDto.convertTaskToTaskDtoResponse(task);


    }

    @Override
    public TaskDtoRequest save(TaskDtoRequest taskDtoRequest) {
        Task task = mapperDto.convertTaskDtoRequestToTask(taskDtoRequest);
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(taskDtoRequest.getStatus_id()).orElseThrow(() -> new NotFoundException("Status id not found - " + taskDtoRequest.getStatus_id())));
        Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(taskDtoRequest.getPriority_id()).orElseThrow(() -> new NotFoundException("Priority id not found - " + taskDtoRequest.getPriority_id())));

        task.setPriority(priority.get());

        task.setStatus(status.get());

        taskRepository.save(task);
        return mapperDto.convertTaskToTaskDtoRequest(task);
    }

    @Override
    public void deleteById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        taskRepository.delete(task);
    }

    @Override
    public TaskDtoResponse updateTask(TaskDtoRequest taskDtoRequest, MultipartFile multipartFile) throws IOException {

        Task task = taskRepository.findById(taskDtoRequest.getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + taskDtoRequest.getId()));
        //task.setId(taskDtoRequest.getId());
        Status status = statusRepository.findById(taskDtoRequest.getStatus_id()).orElseThrow(() -> new NotFoundException("Task id is not found - " + taskDtoRequest.getId()));
        Priority priority = priorityRepository.findById(taskDtoRequest.getPriority_id()).orElseThrow(() -> new NotFoundException("Task id is not found - " + taskDtoRequest.getId()));
        task.setStatus(status);
        task.setPriority(priority);
        task.setTask_name(taskDtoRequest.getTask_name());
        task.setStart_date(taskDtoRequest.getStart_date());

        if (status.getStatus_name().equals("DONE")) {
            task.setEndDate(LocalDateTime.now());
        }
        if (Objects.nonNull(multipartFile)) {
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


        return mapperDto.convertTaskToTaskDtoResponse(taskRepository.save(task));

    }
}
//    @Override
//    public TaskDtoRequest update(TaskDtoRequest taskDtoRequest) {
//        Task task = mapperDto.convertTaskDtoRequestToTask(taskDtoRequest);
//        Optional<Status> status = Optional.ofNullable(statusRepository.findById(taskDtoRequest.getStatus_id()).orElseThrow(() -> new NotFoundException("Status id not found - " + taskDtoRequest.getStatus_id())));
//        Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(taskDtoRequest.getPriority_id()).orElseThrow(() -> new NotFoundException("Priority id not found - " + taskDtoRequest.getPriority_id())));
//
//        task.setPriority(priority.get());
//
//        task.setStatus(status.get());
//
//        taskRepository.save(task);
//        return mapperDto.convertTaskToTaskDtoRequest(task);
//    }
//}