package com.example.ToDoList.service;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<CommentDto> getAllComments();

    public Optional<Comment> findById(int id);

    CommentDto save(CommentDto commentDto);

    void deletebyId(int id);
}
