package com.example.ToDoList.service;

import com.example.ToDoList.DTO.CommentDtoResponse;
import com.example.ToDoList.DTO.CommentDtoRequest;

import java.util.List;

public interface CommentService {

    public List<CommentDtoResponse> getAllComments();

    public CommentDtoResponse findById(Long id);

    public CommentDtoResponse save(CommentDtoRequest commentDto);

    public void delete(Long id);

    public void updateComment(Long id, CommentDtoRequest commentDto);
}
