package com.example.ToDoList.service;

import com.example.ToDoList.DAO.CommentRepository;
import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository theComm){
        commentRepository = theComm;
    }


    private CommentDto commentD(Comment comment){

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setComment(comment.getComment());

        return commentDto;
    }
    private Comment comment(CommentDto commentD){

        Comment comment = new Comment();
        comment.setId(commentD.getId());
        comment.setComment(commentD.getComment());
        return comment;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        List <CommentDto> cDto = new ArrayList<>();
        for (Comment c : commentList){
            CommentDto commDto = commentD(c);
            cDto.add(commDto);
        }  return cDto;
        }

    @Override
    public Optional<Comment> findById(int id) {
        return commentRepository.findById(id);
    }

    @Override
    public void save(CommentDto commentDto) {
        Comment c = comment(commentDto);
        commentRepository.save(c);
    }

    @Override
    public void deletebyId(int id) {
    }
}
