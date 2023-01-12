package com.example.ToDoList.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CommentDtoResponse {
    private Long id;
    private String comment_name;
    private TaskDtoResponse task_dto;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public TaskDtoResponse getTask_dto() {
        return task_dto;
    }

    public void setTask_dto(TaskDtoResponse task_dto) {
        this.task_dto = task_dto;
    }
}
