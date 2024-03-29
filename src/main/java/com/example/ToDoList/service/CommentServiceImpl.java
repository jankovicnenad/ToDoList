package com.example.ToDoList.service;

import com.example.ToDoList.DAO.CommentRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.CommentDtoResponse;
import com.example.ToDoList.DTO.CommentDtoRequest;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private TaskRepository taskRepository;

    private final MapperDto mapperDto;

    public CommentServiceImpl(CommentRepository theComm, TaskRepository theTask, MapperDto mapperDto) {
        commentRepository = theComm;
        taskRepository = theTask;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<CommentDtoResponse> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentDtoResponse> cDto = new ArrayList<>();
        for (Comment c : commentList) {
            CommentDtoResponse commDto = mapperDto.convertCommentToCommentDtoResponse(c);
            cDto.add(commDto);
        }
        return cDto;
    }

    @Override
    public CommentDtoResponse findById(Long id) {

        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id is not found - " + id)));
        CommentDtoResponse commentDtoResponse = mapperDto.convertCommentToCommentDtoResponse(comment.get());
        return commentDtoResponse;
    }

    @Override
    public CommentDtoResponse save(CommentDtoRequest commentDtoRequest) {
        Comment c = mapperDto.convertCommentDtoRequestToComment(commentDtoRequest);
        Optional<Task> task = taskRepository.findById(commentDtoRequest.getTask_id());
        c.setTask(task.get());
        commentRepository.save(c);
        return mapperDto.convertCommentToCommentDtoResponse(c);
    }

    @Override
    public void delete(Long id) {
        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
        commentRepository.delete(comment.get());
    }

    @Override
    public CommentDtoResponse updateComment(CommentDtoRequest commentDtoRequest) {
        Comment comment = commentRepository.findById(commentDtoRequest.getId()).orElseThrow(() -> new NotFoundException("Comment id is not found - " + commentDtoRequest.getId()));
        Task task = taskRepository.findById(commentDtoRequest.getTask_id()).orElseThrow(() -> new NotFoundException("Task id is not found - " + commentDtoRequest.getId()));
        comment.setComment_name(commentDtoRequest.getComment_name());
        comment.setTask(task);
        return mapperDto.convertCommentToCommentDtoResponse(commentRepository.save(comment));
    }

}
