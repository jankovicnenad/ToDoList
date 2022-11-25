package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.service.CommentServiceImpl;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl theComm)
    {commentService = theComm;}

    @GetMapping("/comments")
    public List<CommentDto> getAllComments(){
     return commentService.getAllComments();
    }
    @PostMapping("/comment")
    public CommentDto addComment(@RequestBody CommentDto commDto)
    {
        commDto.setId(0);
        commentService.save(commDto);
        return commDto;
    }

}
