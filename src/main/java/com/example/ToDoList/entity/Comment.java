package com.example.ToDoList.entity;

import javax.persistence.*;

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
        private int id;
    @Column(name = "comment")
        private String comment;
    @OneToOne(mappedBy = "Comment", cascade = CascadeType.ALL)
    private Task taks;

    public Comment(){}

    public Comment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Task getTaks() {
        return taks;
    }

    public void setTaks(Task taks) {
        this.taks = taks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
