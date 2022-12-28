package com.example.ToDoList.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDto {

    private Long id;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime end_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    private StatusDto status_dto;

    private PriorityDto priority_dto;


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

    public LocalDateTime getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public StatusDto getStatus_dto() {
        return status_dto;
    }

    public void setStatus_dto(StatusDto status_dto) {
        this.status_dto = status_dto;
    }

    public PriorityDto getPriority_dto() {
        return priority_dto;
    }

    public void setPriority_dto(PriorityDto priority_dto) {
        this.priority_dto = priority_dto;
    }

}

