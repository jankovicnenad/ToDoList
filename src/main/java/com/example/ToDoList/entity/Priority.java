package com.example.ToDoList.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="priority")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="priority")
    private String priority;
    @OneToMany(mappedBy = "priority", cascade = CascadeType.ALL)
    private List<Task> priorities;
    public Priority(){}

    public Priority(String priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<Task> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<Task> priorities) {
        this.priorities = priorities;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "id=" + id +
                ", priority='" + priority + '\'' +
                '}';
    }
}
