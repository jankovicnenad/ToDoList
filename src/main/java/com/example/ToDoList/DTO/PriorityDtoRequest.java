package com.example.ToDoList.DTO;

public class PriorityDtoRequest {

    private Long id;

    private String priority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
