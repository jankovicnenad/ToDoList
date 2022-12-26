package com.example.ToDoList.DAO;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
