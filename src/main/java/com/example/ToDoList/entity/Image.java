package com.example.ToDoList.entity;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "url")
    private String url;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_task")
    private Task task;

    @Column(name = "original_name")
    private String originalName;

    public Image (){}

    public Image(String description, String url) {
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
