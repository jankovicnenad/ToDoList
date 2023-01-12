package com.example.ToDoList.service;

import com.example.ToDoList.DTO.*;
import com.example.ToDoList.entity.*;
import org.springframework.stereotype.Service;

@Service
public class MapperDtoImpl implements MapperDto {

    @Override
    public Task convertTaskDtoResponseToTask(TaskDtoResponse taskDtoResponse) {
        Task task = new Task();
        task.setId(taskDtoResponse.getId());
        task.setTask_name(taskDtoResponse.getTask_name());
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
        taskDtoResponse.setTask_name(task.getTask_name());
        taskDtoResponse.setStart_date(task.getStart_date());
        taskDtoResponse.setEnd_date(task.getEndDate());
        taskDtoResponse.setCreatedDate(task.getCreatedDate());
        taskDtoResponse.setModifiedDate(task.getModifiedDate());

        if(task.getImages().isEmpty()){
            taskDtoResponse.setImageUrl("No Image");
        }
        else
            taskDtoResponse.setImageUrl(task.getImages().stream().skip(task.getImages().size() - 1).findFirst().get().getUrl());

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(task.getPriority().getId());
        priorityDtoResponse.setPriority_name(task.getPriority().getPriority_name());
        priorityDtoResponse.setModifiedDate(task.getPriority().getModifiedDate());
        priorityDtoResponse.setCreatedDate(task.getPriority().getCreatedDate());
        taskDtoResponse.setPriority_dto(priorityDtoResponse);

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();
        statusDtoResponse.setId(task.getStatus().getId());
        statusDtoResponse.setStatus_name(task.getStatus().getStatus_name());
        statusDtoResponse.setModifiedDate(task.getStatus().getModifiedDate());
        statusDtoResponse.setCreatedDate(task.getStatus().getCreatedDate());
        taskDtoResponse.setStatus_dto(statusDtoResponse);
        return taskDtoResponse;
    }

    @Override
    public Task convertTaskDtoRequestToTask(TaskDtoRequest taskDtoRequest) {
        Task task = new Task();

        task.setId(taskDtoRequest.getId());
        task.setTask_name(taskDtoRequest.getTask_name());
        task.setStart_date(taskDtoRequest.getStart_date());
        Priority priority = new Priority();
        priority.setId(taskDtoRequest.getPriority_id());
        task.setPriority(priority);

        Status status = new Status();
        status.setId(taskDtoRequest.getStatus_id());
        task.setStatus(status);

        return task;
    }

    @Override
    public TaskDtoRequest convertTaskToTaskDtoRequest(Task task) {

        TaskDtoRequest taskDtoRequest = new TaskDtoRequest();

        taskDtoRequest.setId(task.getId());
        taskDtoRequest.setTask_name(task.getTask_name());
        taskDtoRequest.setStart_date(task.getStart_date());
        taskDtoRequest.setPriority_id(task.getPriority().getId());
        taskDtoRequest.setStatus_id(task.getStatus().getId());
        return taskDtoRequest;
    }

    @Override
    public Priority convertPriorityDtoResponseToPriority(PriorityDtoResponse priorityDtoResponse) {
        Priority priority = new Priority();
        priority.setId(priorityDtoResponse.getId());
        priority.setPriority_name(priorityDtoResponse.getPriority_name());
        priority.setModifiedDate(priorityDtoResponse.getModifiedDate());
        priority.setCreatedDate(priority.getCreatedDate());
        return priority;
    }

    @Override
    public PriorityDtoResponse convertPriorityToPriorityDtoResponse(Priority priority) {
        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();
        priorityDtoResponse.setId(priority.getId());
        priorityDtoResponse.setPriority_name(priority.getPriority_name());
        priorityDtoResponse.setCreatedDate(priority.getCreatedDate());
        priorityDtoResponse.setModifiedDate(priority.getModifiedDate());
        return priorityDtoResponse;
    }



    @Override
    public Status convertStatusDtoResponseToStatus(StatusDtoResponse statusD) {
        Status status = new Status();
        status.setId(statusD.getId());
        status.setStatus_name(statusD.getStatus_name());
        status.setCreatedDate(statusD.getCreatedDate());
        status.setModifiedDate(statusD.getModifiedDate());
        return status;
    }

    @Override
    public StatusDtoResponse convertStatusToStatusDtoResponse(Status status) {
        StatusDtoResponse statustDto = new StatusDtoResponse();
        statustDto.setId(status.getId());
        statustDto.setStatus_name(status.getStatus_name());
        statustDto.setCreatedDate(status.getCreatedDate());
        statustDto.setModifiedDate(status.getModifiedDate());
        return statustDto;
    }

    @Override
    public Status convertStatusDtoRequestToStatus(StatusDtoRequest statusDtoRequest) {
        Status status = new Status();
        status.setId(statusDtoRequest.getId());
        status.setStatus_name(statusDtoRequest.getStatus_name());
        return status;
    }

    @Override
    public StatusDtoRequest convertStatusToStatusDtoRequest(Status status) {
        StatusDtoRequest statusDtoRequest = new StatusDtoRequest();
        statusDtoRequest.setId(status.getId());
        statusDtoRequest.setStatus_name(status.getStatus_name());
        return statusDtoRequest;
    }

    @Override
    public Comment convertCommentDtoResponseToComment(CommentDtoResponse commentDtoResponse) {
        Comment comment = new Comment();
        comment.setId(commentDtoResponse.getId());
        comment.setComment_name(commentDtoResponse.getComment_name());
        comment.setCreatedDate(commentDtoResponse.getCreatedDate());
        comment.setModifiedDate(commentDtoResponse.getModifiedDate());


        return comment;
    }

    @Override
    public CommentDtoResponse convertCommentToCommentDtoResponse(Comment comment) {
        CommentDtoResponse commentDtoResponse = new CommentDtoResponse();

        commentDtoResponse.setId(comment.getId());
        commentDtoResponse.setComment_name(comment.getComment_name());
        commentDtoResponse.setCreatedDate(comment.getCreatedDate());
        commentDtoResponse.setModifiedDate(comment.getModifiedDate());

        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();

        taskDtoResponse.setId(comment.getTask().getId());
        taskDtoResponse.setTask_name(comment.getTask().getTask_name());
        taskDtoResponse.setStart_date(comment.getTask().getStart_date());
        taskDtoResponse.setEnd_date(comment.getTask().getEndDate());
        taskDtoResponse.setModifiedDate(comment.getTask().getModifiedDate());
        taskDtoResponse.setCreatedDate(comment.getTask().getCreatedDate());

        StatusDtoResponse statusDtoResponse = new StatusDtoResponse();

        statusDtoResponse.setId(comment.getTask().getStatus().getId());
        statusDtoResponse.setStatus_name(comment.getTask().getStatus().getStatus_name());
        statusDtoResponse.setCreatedDate(comment.getTask().getStatus().getCreatedDate());
        statusDtoResponse.setModifiedDate(comment.getTask().getStatus().getModifiedDate());
        taskDtoResponse.setStatus_dto(statusDtoResponse);

        PriorityDtoResponse priorityDtoResponse = new PriorityDtoResponse();

        priorityDtoResponse.setId(comment.getTask().getPriority().getId());
        priorityDtoResponse.setPriority_name(comment.getTask().getPriority().getPriority_name());
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
        comment.setComment_name(commentDtoRequest.getComment_name());
        Task task = new Task();
        task.setId(commentDtoRequest.getTask_id());
        comment.setTask(task);
        return comment;
    }

    @Override
    public CommentDtoRequest convertCommentToCommentDtoRequest(Comment comment) {
        CommentDtoRequest commentDtoRequest = new CommentDtoRequest();
        commentDtoRequest.setId(comment.getId());
        commentDtoRequest.setComment_name(comment.getComment_name());
        commentDtoRequest.setTask_id(comment.getTask().getId());
        return commentDtoRequest;
    }


    public ImageDTO convertImageToImageDto(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        imageDTO.setOriginalName(image.getOriginalName());
        TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setId(image.getTask().getId());
        taskDtoResponse.setTask_name(image.getTask().getTask_name());
        taskDtoResponse.setStart_date(image.getTask().getStart_date());
        taskDtoResponse.setEnd_date(image.getTask().getEndDate());
        imageDTO.setTask_dto(taskDtoResponse);
        return imageDTO;
    }

    public Image convertImageDtoToImage(ImageDTO imageDTO) {
        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setUrl(imageDTO.getUrl());
        image.setOriginalName(imageDTO.getOriginalName());
        Task task = new Task();
        task.setId(imageDTO.getTask_dto().getId());
        task.setTask_name(imageDTO.getTask_dto().getTask_name());
        task.setStart_date(imageDTO.getTask_dto().getStart_date());
        task.setEndDate(imageDTO.getTask_dto().getEnd_date());
        image.setTask(task);
        return image;
    }

    @Override
    public Priority convertPriorityDtoRequestToPriority(PriorityDtoRequest priorityDtoRequest) {
        Priority priority = new Priority();
        priority.setPriority_name(priorityDtoRequest.getPriority_name());
        priority.setId(priorityDtoRequest.getId());
        return priority;
    }

    @Override
    public PriorityDtoRequest convertPriorityToPriorityDtoRequest(Priority priority) {
        PriorityDtoRequest priorityDtoRequest = new PriorityDtoRequest();

        priorityDtoRequest.setPriority_name(priority.getPriority_name());
        priorityDtoRequest.setId(priority.getId());
        return priorityDtoRequest;

    }

}
