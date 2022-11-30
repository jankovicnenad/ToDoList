package com.example.ToDoList.DTO;

public class CommentDto {
    private int id;
    private String comment;

    private TaskDto task_dto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public TaskDto getTask_dto() {
        return task_dto;
    }

    public void setTask_dto(TaskDto task_dto) {
        this.task_dto = task_dto;
    }
}
