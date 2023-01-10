package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDtoResponse;
import com.example.ToDoList.DTO.TaskDtoRequest;
import com.example.ToDoList.service.ImageService;
import com.example.ToDoList.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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

    @Operation(summary = "This is to fetch all tasks from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched all tasks from database", content = {@Content(mediaType = "application/json")})})
    @PostMapping(value = "/tasks", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile multipartFile, @RequestPart TaskDtoRequest taskDtoRequest) throws Exception {
        if (multipartFile.isEmpty()) return new ResponseEntity<>(taskService.save(taskDtoRequest), HttpStatus.OK);
        else {
            if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".png")) {
                // Ako nije, vratite gresku
//                    return "Invalid file extension";
            }
            System.out.println(imageService.uploadFile(multipartFile));

        }
        return new ResponseEntity<>(taskService.saveImage(taskDtoRequest, multipartFile), HttpStatus.OK);
    }

    @Operation(summary = "This is to fetch all tasks from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched all tasks from database", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/tasks")
    public List<TaskDtoResponse> getAllTasks(@RequestParam(required = false, name = "priority") Long priorityId, @RequestParam(required = false, name = "status") Long statusId) {
        return taskService.getAllTasks(priorityId, statusId);
    }

    @Operation(summary = "This is to update task from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated task from database", content = {@Content(mediaType = "application/json")})})
    @PutMapping("/tasks")
    public String updateTask(@RequestBody TaskDtoRequest taskDto) throws IOException {
        taskService.updateTask(taskDto);
        return "Updated task";
    }

    @Operation(summary = "This is to fetch task from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched task from database", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/tasks/{taskId}")
    public TaskDtoResponse getTasks(@PathVariable Long taskId) {
        TaskDtoResponse taskDtoResponse = taskService.findById(taskId);
        if (taskDtoResponse == null) {
            throw new NotFoundException("Task id not found - " + taskId);
        }
        return taskDtoResponse;
    }

    @Operation(summary = "This is to delete tasks in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted task from database", content = {@Content(mediaType = "application/json")})})
    @DeleteMapping("/tasks/{taskId}")
    public String deleteTasks(@PathVariable Long taskId) {
        TaskDtoResponse taskDtoResponse = taskService.findById(taskId);
        if (taskDtoResponse == null) throw new NotFoundException("Task id not found - " + taskId);
        taskService.deleteById(taskId);
        return "Deleted task with id - " + taskId;
    }


}
