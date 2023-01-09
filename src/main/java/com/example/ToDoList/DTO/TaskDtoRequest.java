package com.example.ToDoList.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class TaskDtoRequest {

    private Long id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate start_date;

    private Long statusID;

    private Long priorityID;

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

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public Long getPriorityID() {
        return priorityID;
    }

    public void setPriorityID(Long priorityID) {
        this.priorityID = priorityID;
    }
}