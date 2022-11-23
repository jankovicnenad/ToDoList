package com.example.ToDoList.DAO;

import com.example.ToDoList.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
