package com.example.ToDoList.DTO;

public class PriorityDtoRequest {

    private Long id;

    private String priority_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriority_name() {
        return priority_name;
    }

    public void setPriority_name(String priority_name) {
        this.priority_name = priority_name;
    }
}
