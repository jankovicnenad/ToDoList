package com.example.ToDoList.DTO;

import java.util.Date;

public class TaskDto {

    private int id;
    private String name;

    private Date startDate;

    private Date endDate;

    private PriorityDto priorityDto;

    private StatusDto statusDto;

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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PriorityDto getPriorityDto() {
        return priorityDto;
    }

    public void setPriorityDto(PriorityDto priorityDto) {
        this.priorityDto = priorityDto;
    }

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }
}

