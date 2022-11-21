package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
}
