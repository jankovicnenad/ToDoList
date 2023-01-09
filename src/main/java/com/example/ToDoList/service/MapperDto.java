package com.example.ToDoList.service;

import com.example.ToDoList.DTO.*;
import com.example.ToDoList.entity.*;

public interface MapperDto {
    public Task convertTaskDtoToTask(TaskDto taskDto);

    public TaskDto convertTaskToTaskDto(Task task);

    public Priority convertPriorityDtoToPriority(PriorityDto priorityDto);

    public PriorityDto convertPriorityToPriorityDto(Priority priority);

    public Status convertStatusDtoToStatus(StatusDto statusD);

    public StatusDto convertStatusToStatusDto(Status status);

    public Comment convertCommentDtoToComment(CommentDto commentDto);

    public CommentDto convertCommentToCommentDto(Comment comment);

    public Task convertTaskDtoRequestToTask(TaskDto taskDtoRequest);

    TaskDtoRequest convertTaskToTaskDtoRequest(Task task);

    public ImageDTO convertImageToImageDto(Image image);

    public Image convertImageDtoToImage(ImageDTO imageDTO);

}
