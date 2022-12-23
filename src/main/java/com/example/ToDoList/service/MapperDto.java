package com.example.ToDoList.service;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;

public interface MapperDto {
    public Task convertTaskDtoToTask(TaskDto taskDto);

    public TaskDto convertTaskToTaskDto(Task task);

    public Priority convertPriorityDtoToPriority(PriorityDto priorityDto);

    public PriorityDto convertPriorityToPriorityDto(Priority priority);

    public Status convertStatusDtoToStatus(StatusDto statusD);

    public StatusDto convertStatusToStatusDto(Status status);

    public Comment convertCommentDtoToComment(CommentDto commentDto);

    public CommentDto convertCommentToCommentDto(Comment comment);
}
