package com.example.ToDoList.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "comment")
    private String comment_name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "task_id")
    private Task task;

    public Comment() {
    }

    public Comment(String comment_name) {
        this.comment_name = comment_name;
    }

    public Long getId() {
        return id;
    }

    public String getComment_name() {
        return comment_name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComment_name(String comment_name) {
        this.comment_name = comment_name;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", comment='" + comment_name + '\'' + '}';
    }
}