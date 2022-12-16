package com.example.ToDoList.DTO;

public class ImageDTO {

    private int id;
    private String description;

    private String url;

    private TaskDto task_dto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TaskDto getTask_dto() {
        return task_dto;
    }

    public void setTask_dto(TaskDto task_dto) {
        this.task_dto = task_dto;
    }
}
