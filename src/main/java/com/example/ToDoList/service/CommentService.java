package com.example.ToDoList.service;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.DTO.CommentDtoRequest;
import com.example.ToDoList.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<CommentDto> getAllComments();

    public CommentDto findById(Long id);

    public CommentDto save(CommentDtoRequest commentDto);

    public void delete(Long id);

    public void updateComment(Long id, CommentDtoRequest commentDto);
}
