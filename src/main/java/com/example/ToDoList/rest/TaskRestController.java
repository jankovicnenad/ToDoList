package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDtoResponse;
import com.example.ToDoList.DTO.TaskDtoRequest;
import com.example.ToDoList.service.ImageService;
import com.example.ToDoList.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    private final TaskServiceImpl taskService;

    private final TaskRepository taskRepository;

    private final ImageService imageService;

    public TaskRestController(TaskServiceImpl TheTask, TaskRepository theRepo, ImageService imageService) {
        taskService = TheTask;
        taskRepository = theRepo;
        this.imageService = imageService;
    }

    @Operation(summary = "Insert task with multipart file (not required)")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Inserted tasks", content = {@Content(mediaType = "application/json")})})
    @PostMapping(value = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveTask(@RequestPart(value = "file", required = false) MultipartFile multipartFile, @RequestPart TaskDtoRequest taskDtoRequest) throws Exception {
        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) {
            return new ResponseEntity<>(taskService.save(taskDtoRequest), HttpStatus.OK);
        } else if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".png")) {
            throw new BadRequestException("Invalid image type!");
        }
//            System.out.println(imageService.uploadFile(multipartFile));
        return new ResponseEntity<>(taskService.saveTask(taskDtoRequest, multipartFile), HttpStatus.OK);
    }


    @Operation(summary = " Fetch all tasks from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched all tasks from database")})
    @GetMapping("/tasks")
    public List<TaskDtoResponse> getAllTasks(@RequestParam(required = false, name = "priority") Long priorityId, @RequestParam(required = false, name = "status") Long statusId) {
        return taskService.getAllTasks(priorityId, statusId);
    }

    @Operation(summary = "Update task with multipart file (not required)")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated task in database", content = {@Content(mediaType = "application/json")})})
    @PutMapping(value = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateTask(@RequestPart(value = "file", required = false) MultipartFile multipartFile, @RequestPart TaskDtoRequest taskDtoRequest) throws IOException {
        if (Objects.isNull(multipartFile) || multipartFile.isEmpty()) {
            return new ResponseEntity<>(taskService.updateTask(taskDtoRequest,multipartFile), HttpStatus.OK);
        } else if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".png")) {
            throw new BadRequestException("Invalid image type!");
        }
        taskService.updateTask(taskDtoRequest, multipartFile);
        return new ResponseEntity<>(taskService.updateTask(taskDtoRequest, multipartFile), HttpStatus.OK);
    }

    @Operation(summary = "Get task from database with specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched task from database with specific ID")})
    @GetMapping("/tasks/{taskId}")
    public TaskDtoResponse getTasks(@PathVariable Long taskId) {
        TaskDtoResponse taskDtoResponse = taskService.findById(taskId);
        if (taskDtoResponse == null) {
            throw new NotFoundException("Task id not found - " + taskId);
        }
        return taskDtoResponse;
    }

    @Operation(summary = "Delete task in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted task from database")})
    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTasks(@PathVariable Long taskId) {
        TaskDtoResponse taskDtoResponse = taskService.findById(taskId);
        if (taskDtoResponse == null) throw new NotFoundException("Task id not found - " + taskId);
        taskService.deleteById(taskId);
        return new ResponseEntity<>(Collections.singletonMap("message", "deleted task with id: " + taskId), HttpStatus.OK);
    }


}
