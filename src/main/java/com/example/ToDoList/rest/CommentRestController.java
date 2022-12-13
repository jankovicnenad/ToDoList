package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.service.CommentServiceImpl;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "192.168.0.105")
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

    @GetMapping("/comments/{commentId}")
    public CommentDto findByid(@PathVariable int commentId){
        CommentDto commentDto = commentService.findById(commentId);
        if(commentDto == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        return commentDto;
    }
    @PutMapping("/comments/{commentId}")
    public String updateComment(@PathVariable int commentId, @RequestBody CommentDto commentDto){
        commentService.updateComment(commentId, commentDto);
        return "Updated comment with id - " +commentId;
    }
    @PostMapping("/comments")
    public CommentDto addComment(@RequestBody CommentDto commDto)
    {
        commentService.save(commDto);
        return commDto;
    }
    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(@PathVariable int commentId){

        CommentDto commentDto = commentService.findById(commentId);
        if(commentDto == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        commentService.delete(commentId);
        return "Deleted comment with id - " +commentId;
    }

}
