package com.example.ToDoList.DTO;

import java.util.Date;

public class TaskDto {

    private int id;
    private String name;

    private Date start_date;

    private Date end_date;

    private StatusDto status_dto;

    private PriorityDto priority_dto;


    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

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

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
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

