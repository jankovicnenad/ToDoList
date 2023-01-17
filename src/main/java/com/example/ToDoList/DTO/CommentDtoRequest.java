package com.example.ToDoList.DTO;

public class CommentDtoRequest {

    private Long id;
    private String comment_name;
    // private TaskDtoResponse taskDto;

    private Long task_id;

    public CommentDtoRequest() {
    }

    public CommentDtoRequest(Long id, String comment_name, Long task_id) {
        this.id = id;
        this.comment_name = comment_name;
        this.task_id = task_id;
    }

    public CommentDtoRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }
}