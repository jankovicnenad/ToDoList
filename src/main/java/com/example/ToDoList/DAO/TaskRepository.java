package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path="tasks")
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
