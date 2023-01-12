package com.example.ToDoList.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="priority")
public class Priority  extends  Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="priority")
    private String priority_name;
    @OneToMany(mappedBy = "priority")
    private List<Task> tasks = new ArrayList<>();
    public Priority(){}

    public Priority(String priority_name) {
        this.priority_name = priority_name;
    }

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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks)
    {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", priority='" + priority_name + '\'' +
                '}';
    }
}
