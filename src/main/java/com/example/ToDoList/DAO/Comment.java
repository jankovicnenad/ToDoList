package com.example.ToDoList.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Comment extends JpaRepository<Comment, Integer> {
}
