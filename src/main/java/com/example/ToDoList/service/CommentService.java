package com.example.ToDoList.service;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<CommentDto> getAllComments();

    public CommentDto findById(int id);

    public CommentDto save(CommentDto commentDto);

    public void delete(int id);
}
