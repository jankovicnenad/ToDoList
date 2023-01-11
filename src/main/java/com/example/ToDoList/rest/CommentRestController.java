package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.CommentDtoResponse;
import com.example.ToDoList.DTO.CommentDtoRequest;
import com.example.ToDoList.service.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "192.168.0.105")
@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl theComm)
    {commentService = theComm;}

    @GetMapping("/comments")
    public List<CommentDtoResponse> getAllComments(){
     return commentService.getAllComments();
    }

    @GetMapping("/comments/{commentId}")
    public CommentDtoResponse findByid(@PathVariable Long commentId){
        CommentDtoResponse commentDtoResponse = commentService.findById(commentId);
        if(commentDtoResponse == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        return commentDtoResponse;
    }
    @PutMapping("/comments")
    public String updateComment(@RequestBody CommentDtoRequest commentDto){
        Long commentId = commentDto.getId();
        commentService.updateComment(commentId, commentDto);
        return "Updated comment with id - " +commentId;
    }
    @PostMapping("/comments")
    public void addComment(@RequestBody CommentDtoRequest commDto)
    {
        commentService.save(commDto);
    }
    @DeleteMapping("/comments/{commentId}")
    public String deleteComment(@PathVariable Long commentId){

        CommentDtoResponse commentDtoResponse = commentService.findById(commentId);
        if(commentDtoResponse == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        commentService.delete(commentId);
        return "Deleted comment with id - " +commentId;
    }

}
