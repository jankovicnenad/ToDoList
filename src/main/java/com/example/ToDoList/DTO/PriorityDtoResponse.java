package com.example.ToDoList.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PriorityDtoResponse {

    private Long id;
    private String priority_name;

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

    public String getPriority_name() {
        return priority_name;
    }

    public void setPriority_name(String priority_name) {
        this.priority_name = priority_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
