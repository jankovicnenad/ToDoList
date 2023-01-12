package com.example.ToDoList.service;

import com.example.ToDoList.DTO.*;
import com.example.ToDoList.entity.*;

public interface MapperDto {
    public Task convertTaskDtoResponseToTask(TaskDtoResponse taskDtoResponse);

    public TaskDtoResponse convertTaskToTaskDtoResponse(Task task);

    public Task convertTaskDtoRequestToTask(TaskDtoRequest taskDtoRequest);

    public TaskDtoRequest convertTaskToTaskDtoRequest(Task task);

    public Priority convertPriorityDtoResponseToPriority(PriorityDtoResponse priorityDtoResponse);

    public PriorityDtoResponse convertPriorityToPriorityDtoResponse(Priority priority);

    public Status convertStatusDtoResponseToStatus(StatusDtoResponse statusD);

    public StatusDtoResponse convertStatusToStatusDtoResponse(Status status);

    public Status convertStatusDtoRequestToStatus(StatusDtoRequest statusD);

    public StatusDtoRequest convertStatusToStatusDtoRequest(Status status);

    public Comment convertCommentDtoResponseToComment(CommentDtoResponse commentDtoResponse);

    public CommentDtoResponse convertCommentToCommentDtoResponse(Comment comment);

    public Comment convertCommentDtoRequestToComment(CommentDtoRequest commentDto);

    public CommentDtoRequest convertCommentToCommentDtoRequest(Comment comment);


    public ImageDTO convertImageToImageDto(Image image);

    public Image convertImageDtoToImage(ImageDTO imageDTO);

    public Priority convertPriorityDtoRequestToPriority(PriorityDtoRequest priorityDto);

    public PriorityDtoRequest convertPriorityToPriorityDtoRequest(Priority priority);



}
