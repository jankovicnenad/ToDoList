package com.example.ToDoList.service;

import com.example.ToDoList.DTO.*;
import com.example.ToDoList.entity.*;
import org.springframework.stereotype.Service;

@Service
public class MapperDtoImpl implements MapperDto{

    @Override
    public Task convertTaskDtoResponseToTask(TaskDtoResponse taskDtoResponse) {
        Task task = new Task();
        task.setId(taskDtoResponse.getId());
        task.setName(taskDtoResponse.getName());
        task.setStart_date(taskDtoResponse.getStart_date());
        task.setEndDate(taskDtoResponse.getEnd_date());
        task.setCreatedDate(taskDtoResponse.getCreatedDate());
        task.setModifiedDate(taskDtoResponse.getModifiedDate());

        Status status = new Status();
        status.setId(taskDtoResponse.getStatus_dto().getId());

        Priority priority = new Priority();
        priority.setId(taskDtoResponse.getPriority_dto().getId());

        task.setStatus(status);
        task.setPriority(priority);
        return task;
    }

    @Override
    public TaskDtoResponse convertTaskToTaskDtoResponse(Task task) {
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();

        taskDtoResponse.setId(task.getId());
        taskDtoResponse.setName(task.getName());
        taskDtoResponse.setStart_date(task.getStart_date());
        taskDtoResponse.setEnd_date(task.getEndDate());
        taskDtoResponse.setCreatedDate(task.getCreatedDate());
        taskDtoResponse.setModifiedDate(task.getModifiedDate());

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(task.getPriority().getId());
        priorityDtoResponse.setPriority(task.getPriority().getPriority());
        priorityDtoResponse.setModifiedDate(task.getPriority().getModifiedDate());
        priorityDtoResponse.setCreatedDate(task.getPriority().getCreatedDate());
        taskDtoResponse.setPriority_dto(priorityDtoResponse);

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        statusDtoResponse.setId(task.getStatus().getId());
        statusDtoResponse.setStatus_name(task.getStatus().getStatus());
        statusDtoResponse.setModifiedDate(task.getStatus().getModifiedDate());
        statusDtoResponse.setCreatedDate(task.getStatus().getCreatedDate());
        taskDtoResponse.setStatus_dto(statusDtoResponse);
        return taskDtoResponse;    }

    @Override
    public Task convertTaskDtoRequestToTask(TaskDtoRequest taskDtoRequest) {
        Task task = new Task();
        
        task.setId(taskDtoRequest.getId());
        task.setName(taskDtoRequest.getName());
        task.setStart_date(taskDtoRequest.getStart_date());
        task.getPriority().setId(taskDtoRequest.getPriorityID());
        task.getStatus().setId(taskDtoRequest.getStatusID());
        
        return task;
    }

    @Override
    public TaskDtoRequest convertTaskToTaskDtoRequest(Task task) {

        TaskDtoRequest taskDtoRequest = new TaskDtoRequest();

        taskDtoRequest.setId(task.getId());
        taskDtoRequest.setName(task.getName());
        taskDtoRequest.setStart_date(task.getStart_date());
        taskDtoRequest.setPriorityID(task.getPriority().getId());
        taskDtoRequest.setStatusID(task.getStatus().getId());
        return taskDtoRequest;
    }

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
    public Comment convertCommentDtoResponseToComment(CommentDtoResponse commentDtoResponse) {
        Comment comment = new Comment();
        comment.setId(commentDtoResponse.getId());
        comment.setComment(commentDtoResponse.getComment());
        comment.setCreatedDate(commentDtoResponse.getCreatedDate());
        comment.setModifiedDate(commentDtoResponse.getModifiedDate());


        return comment;
    }

    @Override
    public CommentDtoResponse convertCommentToCommentDtoResponse(Comment comment) {
        CommentDtoResponse commentDtoResponse = new CommentDtoResponse();

        commentDtoResponse.setId(comment.getId());
        commentDtoResponse.setComment(comment.getComment());
        commentDtoResponse.setCreatedDate(comment.getCreatedDate());
        commentDtoResponse.setModifiedDate(comment.getModifiedDate());

        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();

        taskDtoResponse.setId(comment.getTask().getId());
        taskDtoResponse.setName(comment.getTask().getName());
        taskDtoResponse.setStart_date(comment.getTask().getStart_date());
        taskDtoResponse.setEnd_date(comment.getTask().getEndDate());
        taskDtoResponse.setModifiedDate(comment.getTask().getModifiedDate());
        taskDtoResponse.setCreatedDate(comment.getTask().getCreatedDate());

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();

        statusDtoResponse.setId(comment.getTask().getStatus().getId());
        statusDtoResponse.setStatus_name(comment.getTask().getStatus().getStatus());
        statusDtoResponse.setCreatedDate(comment.getTask().getStatus().getCreatedDate());
        statusDtoResponse.setModifiedDate(comment.getTask().getStatus().getModifiedDate());
        taskDtoResponse.setStatus_dto(statusDtoResponse);

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();

        priorityDtoResponse.setId(comment.getTask().getPriority().getId());
        priorityDtoResponse.setPriority(comment.getTask().getPriority().getPriority());
        priorityDtoResponse.setCreatedDate(comment.getTask().getPriority().getCreatedDate());
        priorityDtoResponse.setModifiedDate(comment.getTask().getPriority().getModifiedDate());
        taskDtoResponse.setPriority_dto(priorityDtoResponse);

        commentDtoResponse.setTask_dto(taskDtoResponse);
        return commentDtoResponse;
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

  
    public ImageDTO convertImageToImageDto(Image image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setOriginalName(image.getOriginalName());
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setId(image.getTask().getId());
        taskDtoResponse.setName(image.getTask().getName());
        taskDtoResponse.setStart_date(image.getTask().getStart_date());
        taskDtoResponse.setEnd_date(image.getTask().getEndDate());
        imageDTO.setTask_dto(taskDtoResponse);
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
