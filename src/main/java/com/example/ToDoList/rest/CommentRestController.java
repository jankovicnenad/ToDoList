package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.CommentDtoResponse;
import com.example.ToDoList.DTO.CommentDtoRequest;
import com.example.ToDoList.service.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.mapping.Collection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentRestController {

    private final CommentServiceImpl commentService;

    public CommentRestController(CommentServiceImpl theComm)
    {commentService = theComm;}

    @Operation(summary = "Get all comments from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All tasks are extracted from the database")})
    @GetMapping("/comments")
    public List<CommentDtoResponse> getAllComments(){
     return commentService.getAllComments();
    }

    @Operation(summary = "Get comment from database by specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Got comment from database by specific ID")})
    @GetMapping("/comments/{commentId}")
    public CommentDtoResponse findByid(@PathVariable Long commentId){
        CommentDtoResponse commentDtoResponse = commentService.findById(commentId);
        if(commentDtoResponse == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        return commentDtoResponse;
    }


    @Operation(summary = "Update comment in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated comment in database")})
    @PutMapping("/comments")
    public ResponseEntity<?> updateComment(@RequestBody CommentDtoRequest commentDto){
        Long commentId = commentDto.getId();

        return new ResponseEntity<>(commentService.updateComment(commentDto), HttpStatus.OK);
    }

    @Operation(summary = "Save comment in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Saved comment in database")})
    @PostMapping("/comments")
    public CommentDtoResponse saveComment(@RequestBody CommentDtoRequest commDto)
    {
        return commentService.save(commDto);

    }
    @Operation(summary = "Delete comment in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted comment in database")})
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId){

        CommentDtoResponse commentDtoResponse = commentService.findById(commentId);
        if(commentDtoResponse == null)
            throw new NotFoundException("Comment id not found - " +commentId);
        commentService.delete(commentId);
        return new ResponseEntity<>(Collections.singletonMap("message", "deleted comment with id: " + commentId), HttpStatus.OK);
    }

}
