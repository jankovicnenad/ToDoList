package com.example.ToDoList.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="status")
public class Status extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="status")
    private String status_name;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks = new ArrayList<>();

    public Status(){}

    public Status(String status_name) {
        this.status_name = status_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name)
    {
        this.status_name = status_name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", status='" + status_name + '\'' +
                '}';
    }
}
