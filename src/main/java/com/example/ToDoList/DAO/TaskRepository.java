package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
