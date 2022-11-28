package com.example.ToDoList.DTO;

import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;

import java.util.Date;

public class TaskDto {

    private int id;
    private String name;

    private Date startDate;

    private StatusDto statusDto;

    private PriorityDto priorityDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }

    public PriorityDto getPriorityDto() {
        return priorityDto;
    }

    public void setPriorityDto(PriorityDto priorityDto) {
        this.priorityDto = priorityDto;
    }
}

