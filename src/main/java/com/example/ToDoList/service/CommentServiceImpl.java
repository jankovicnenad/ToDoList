package com.example.ToDoList.service;

import com.example.ToDoList.DAO.CommentRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    private TaskRepository taskRepository;

    private final MapperDto mapperDto;

    public CommentServiceImpl(CommentRepository theComm, TaskRepository theTask, MapperDto mapperDto){
        commentRepository = theComm;
        taskRepository = theTask;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List <CommentDto> cDto = new ArrayList<>();
        for (Comment c : commentList){
            CommentDto commDto = mapperDto.convertCommentToCommentDto(c);
            cDto.add(commDto);
        }  return cDto;
        }

    @Override
    public CommentDto findById(int id) {

        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id is not found - " + id)));
        CommentDto commentDto = mapperDto.convertCommentToCommentDto(comment.get());
        return commentDto;
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment c = mapperDto.convertCommentDtoToComment(commentDto);
        Optional<Task> task = taskRepository.findById(commentDto.getTask_dto().getId());
        c.setTask(task.get());
        commentRepository.save(c);
        return mapperDto.convertCommentToCommentDto(c);
    }

    @Override
    public void delete(int id) {
        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
        commentRepository.delete(comment.get());
    }

    @Override
    public void updateComment(int id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Comment comment1 = mapperDto.convertCommentDtoToComment(commentDto);
        comment1.setId(comment.getId());
        Task task = taskRepository.findById(commentDto.getTask_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        comment1.setTask(task);
        commentRepository.save(comment1);
    }

}
