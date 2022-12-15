package com.example.ToDoList.service;

import com.example.ToDoList.DAO.CommentRepository;
import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    private TaskRepository taskRepository;

    public CommentServiceImpl(CommentRepository theComm, TaskRepository theTask){
        commentRepository = theComm;
        taskRepository = theTask;
    }


    private CommentDto convertCommentToCommentDto(Comment comment){

        CommentDto commentDto = new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());

        TaskDto taskDto = new TaskDto();

        taskDto.setId(comment.getTask().getId());
        taskDto.setName(comment.getTask().getName());
        taskDto.setStart_date(comment.getTask().getStart_date());
        taskDto.setEnd_date(comment.getTask().getEndDate());

        StatusDto statusDto = new StatusDto();

        statusDto.setId(comment.getTask().getStatus().getId());
        statusDto.setStatus_name(comment.getTask().getStatus().getStatus());
        taskDto.setStatus_dto(statusDto);

        PriorityDto priorityDto = new PriorityDto();

        priorityDto.setId(comment.getTask().getPriority().getId());
        priorityDto.setPriority(comment.getTask().getPriority().getPriority());
        taskDto.setPriority_dto(priorityDto);

        commentDto.setTask_dto(taskDto);
        return commentDto;
    }

    private Comment convertCommentDtoToComment(CommentDto commentDto) {

        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setComment(commentDto.getComment());


        return comment;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List <CommentDto> cDto = new ArrayList<>();
        for (Comment c : commentList){
            CommentDto commDto = convertCommentToCommentDto(c);
            cDto.add(commDto);
        }  return cDto;
        }

    @Override
    public CommentDto findById(int id) {

        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id is not found - " + id)));
        CommentDto commentDto = convertCommentToCommentDto(comment.get());
        return commentDto;
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        Comment c = convertCommentDtoToComment(commentDto);
        Optional<Task> task = taskRepository.findById(commentDto.getTask_dto().getId());
        c.setTask(task.get());
        commentRepository.save(c);
        return convertCommentToCommentDto(c);
    }

    @Override
    public void delete(int id) {
        Optional<Comment> comment = Optional.ofNullable(commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
        commentRepository.delete(comment.get());
    }

    @Override
    public void updateComment(int id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Comment comment1 = convertCommentDtoToComment(commentDto);
        comment1.setId(comment.getId());
        Task task = taskRepository.findById(commentDto.getTask_dto().getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        comment1.setTask(task);
        commentRepository.save(comment1);
    }

}
