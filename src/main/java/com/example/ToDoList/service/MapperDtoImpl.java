package com.example.ToDoList.service;

import com.example.ToDoList.DTO.*;
import com.example.ToDoList.entity.*;
import liquibase.pro.packaged.T;
import org.springframework.stereotype.Service;

@Service
public class MapperDtoImpl implements MapperDto{

    @Override
    public Task convertTaskDtoToTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setStart_date(taskDto.getStart_date());
        task.setEndDate(taskDto.getEnd_date());
        task.setCreatedDate(taskDto.getCreatedDate());
        task.setModifiedDate(taskDto.getModifiedDate());

        Status status = new Status();
        status.setId(taskDto.getStatus_dto().getId());

        Priority priority = new Priority();
        priority.setId(taskDto.getPriority_dto().getId());

        task.setStatus(status);
        task.setPriority(priority);
        return task;
    }

    @Override
    public TaskDto convertTaskToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();

        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setStart_date(task.getStart_date());
        taskDto.setEnd_date(task.getEndDate());
        taskDto.setCreatedDate(task.getCreatedDate());
        taskDto.setModifiedDate(task.getModifiedDate());

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(task.getPriority().getId());
        priorityDtoResponse.setPriority(task.getPriority().getPriority());
        priorityDtoResponse.setModifiedDate(task.getPriority().getModifiedDate());
        priorityDtoResponse.setCreatedDate(task.getPriority().getCreatedDate());
        taskDto.setPriority_dto(priorityDtoResponse);

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        statusDtoResponse.setId(task.getStatus().getId());
        statusDtoResponse.setStatus_name(task.getStatus().getStatus());
        statusDtoResponse.setModifiedDate(task.getStatus().getModifiedDate());
        statusDtoResponse.setCreatedDate(task.getStatus().getCreatedDate());
        taskDto.setStatus_dto(statusDtoResponse);
        return taskDto;    }

    @Override
    public Priority convertPriorityDtoResponseToPriority(PriorityDtoResponse priorityDtoResponse) {
        Priority priority = new Priority();
        priority.setId(priorityDtoResponse.getId());
        priority.setPriority(priorityDtoResponse.getPriority());
        priority.setModifiedDate(priorityDtoResponse.getModifiedDate());
        priority.setCreatedDate(priority.getCreatedDate());
        return priority;
    }

    @Override
    public PriorityDtoResponse convertPriorityToPriorityDtoResponse(Priority priority) {
        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(priority.getId());
        priorityDtoResponse.setPriority(priority.getPriority());
        priorityDtoResponse.setCreatedDate(priority.getCreatedDate());
        priorityDtoResponse.setModifiedDate(priority.getModifiedDate());
        return priorityDtoResponse;
    }

    @Override
    public Status convertStatusDtoResponseToStatus(StatusDtoResponse statusD) {
        Status status = new Status();
        status.setId(statusD.getId());
        status.setStatus(statusD.getStatus_name());
        status.setCreatedDate(statusD.getCreatedDate());
        status.setModifiedDate(statusD.getModifiedDate());
        return status;
    }

    @Override
    public StatusDtoResponse convertStatusToStatusDtoResponse(Status status) {
        StatusDtoResponse statustDto = new StatusDtoResponse();
        statustDto.setId(status.getId());
        statustDto.setStatus_name(status.getStatus());
        statustDto.setCreatedDate(status.getCreatedDate());
        statustDto.setModifiedDate(status.getModifiedDate());
        return statustDto;
    }

    @Override
    public Status convertStatusDtoRequestToStatus(StatusDtoRequest statusDtoRequest) {
        Status status = new Status();
        status.setId(statusDtoRequest.getId());
        status.setStatus(statusDtoRequest.getStatus());
        return status;
    }

    @Override
    public StatusDtoRequest convertStatusToStatusDtoRequest(Status status) {
        StatusDtoRequest statusDtoRequest = new StatusDtoRequest();
        statusDtoRequest.setId(status.getId());
        statusDtoRequest.setStatus(status.getStatus());
        return statusDtoRequest;
    }

    @Override
    public Comment convertCommentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setComment(commentDto.getComment());
        comment.setCreatedDate(commentDto.getCreatedDate());
        comment.setModifiedDate(commentDto.getModifiedDate());


        return comment;
    }

    @Override
    public CommentDto convertCommentToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());
        commentDto.setCreatedDate(comment.getCreatedDate());
        commentDto.setModifiedDate(comment.getModifiedDate());

        TaskDto taskDto = new TaskDto();

        taskDto.setId(comment.getTask().getId());
        taskDto.setName(comment.getTask().getName());
        taskDto.setStart_date(comment.getTask().getStart_date());
        taskDto.setEnd_date(comment.getTask().getEndDate());
        taskDto.setModifiedDate(comment.getTask().getModifiedDate());
        taskDto.setCreatedDate(comment.getTask().getCreatedDate());

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();

        statusDtoResponse.setId(comment.getTask().getStatus().getId());
        statusDtoResponse.setStatus_name(comment.getTask().getStatus().getStatus());
        statusDtoResponse.setCreatedDate(comment.getTask().getStatus().getCreatedDate());
        statusDtoResponse.setModifiedDate(comment.getTask().getStatus().getModifiedDate());
        taskDto.setStatus_dto(statusDtoResponse);

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();

        priorityDtoResponse.setId(comment.getTask().getPriority().getId());
        priorityDtoResponse.setPriority(comment.getTask().getPriority().getPriority());
        priorityDtoResponse.setCreatedDate(comment.getTask().getPriority().getCreatedDate());
        priorityDtoResponse.setModifiedDate(comment.getTask().getPriority().getModifiedDate());
        taskDto.setPriority_dto(priorityDtoResponse);

        commentDto.setTask_dto(taskDto);
        return commentDto;
    }

    @Override
    public Comment convertCommentDtoRequestToComment(CommentDtoRequest commentDtoRequest) {
        Comment comment = new Comment();
        comment.setId(commentDtoRequest.getId());
        comment.setComment(commentDtoRequest.getComment());
        Task task = new Task();
        task.setId(commentDtoRequest.getTaskID());
        comment.setTask(task);
        return comment;
    }

    @Override
    public CommentDtoRequest convertCommentToCommentDtoRequest(Comment comment) {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest();
        commentDtoRequest.setId(comment.getId());
        commentDtoRequest.setComment(comment.getComment());
        commentDtoRequest.setTaskID(comment.getTask().getId());
        return commentDtoRequest;
    }

    @Override
    public Task convertTaskDtoRequestToTask(TaskDtoRequest taskDtoRequest) {
        Task task = new Task();
        task.setId(taskDtoRequest.getId());
        task.setName(taskDtoRequest.getName());
        task.setStart_date(taskDtoRequest.getStart_date());
        Status status = new Status();
        status.setId(taskDtoRequest.getStatus_dto().getId());
        Priority priority = new Priority();
        priority.setId(taskDtoRequest.getPriority_dto().getId());
        task.setStatus(status);
        task.setPriority(priority);
        return task;
    }

    @Override
    public TaskDtoRequest convertTaskToTaskDtoRequest(Task task) {
        TaskDtoRequest taskDtoRequest = new TaskDtoRequest();
        taskDtoRequest.setId(task.getId());
        taskDtoRequest.setName(task.getName());
        taskDtoRequest.setStart_date(task.getStart_date());
        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        statusDtoResponse.setId(task.getStatus().getId());
        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(task.getPriority().getId());
        taskDtoRequest.setStatus_dto(statusDtoResponse);
        taskDtoRequest.setPriority_dto(priorityDtoResponse);
        return taskDtoRequest;
    }
    public ImageDTO convertImageToImageDto(Image image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setOriginalName(image.getOriginalName());
        TaskDto taskDto = new TaskDto();
        taskDto.setId(image.getTask().getId());
        taskDto.setName(image.getTask().getName());
        taskDto.setStart_date(image.getTask().getStart_date());
        taskDto.setEnd_date(image.getTask().getEndDate());
        imageDTO.setTask_dto(taskDto);
        return imageDTO;
    }
    public Image convertImageDtoToImage(ImageDTO imageDTO){
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setUrl(imageDTO.getUrl());
        image.setOriginalName(imageDTO.getOriginalName());
        Task task = new Task();
        task.setId(imageDTO.getTask_dto().getId());
        task.setName(imageDTO.getTask_dto().getName());
        task.setStart_date(imageDTO.getTask_dto().getStart_date());
        task.setEndDate(imageDTO.getTask_dto().getEnd_date());
        image.setTask(task);
        return image;
    }

    @Override
    public Priority convertPriorityDtoRequestToPriority(PriorityDtoRequest priorityDto) {
        Priority priority = new Priority();
        priority.setPriority(priorityDto.getPriority());
        priority.setId(priorityDto.getId());
        return priority;
    }

    @Override
    public PriorityDtoRequest convertPriorityToPriorityDtoRequest(Priority priority) {
    PriorityDtoRequest priorityDtoRequest = new PriorityDtoRequest();

    priorityDtoRequest.setPriority(priority.getPriority());
    priorityDtoRequest.setId(priority.getId());
    return priorityDtoRequest;

    }

}
