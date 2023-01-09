package com.example.ToDoList.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TaskDtoRequest {

    private Long id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate start_date;

    private StatusDtoResponse status_dto;

    private PriorityDtoResponse priority_dto;

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

    public StatusDtoResponse getStatus_dto() {
        return status_dto;
    }

    public void setStatus_dto(StatusDtoResponse status_dto) {
        this.status_dto = status_dto;
    }

    public PriorityDtoResponse getPriority_dto() {
        return priority_dto;
    }

    public void setPriority_dto(PriorityDtoResponse priority_dto) {
        this.priority_dto = priority_dto;
    }
}
