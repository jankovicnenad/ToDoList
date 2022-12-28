package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.service.TaskServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

        private final TaskServiceImpl taskService;

        private final TaskRepository taskRepository;

        public TaskRestController(TaskServiceImpl TheTask, TaskRepository theRepo)
        {
            taskService = TheTask;
            taskRepository = theRepo;
        }
        @Operation(summary = "This is to post tasks in database")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Posted tasks into database",
                    content = {@Content(mediaType = "application/json")})
            })
        @PostMapping("/tasks")
        public TaskDto addTasks(@RequestBody TaskDto task){

            return taskService.save(task);
        }
        @Operation(summary = "This is to fetch all tasks from database")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                    description = "Fetched all tasks from database",
                    content = {@Content(mediaType = "application/json")})
            })
        @GetMapping("/tasks")
        public List<TaskDto> getAllTasks(@RequestParam(required = false, name = "priority") Long priorityId, @RequestParam(required = false, name = "status") Long statusId){
            return taskService.getAllTasks(priorityId, statusId);
        }
        @Operation(summary = "This is to update task from database")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                    description = "Updated task from database",
                    content = {@Content(mediaType = "application/json")})
            })
        @PutMapping("/tasks")
        public String updateTask(@RequestBody TaskDto taskDto) throws IOException {
            taskService.updateTask(taskDto);
            return "Updated task";
        }
        @Operation(summary = "This is to fetch task from database")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                    description = "Fetched task from database",
                    content = {@Content(mediaType = "application/json")})
                })
        @GetMapping("/tasks/{taskId}")
        public TaskDto getTasks(@PathVariable Long taskId){
            TaskDto taskDto = taskService.findById(taskId);
            if (taskDto == null){
                throw new NotFoundException("Task id not found - " +taskId);
            }
            return taskDto;
        }
        @Operation(summary = "This is to delete tasks in database")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200",
                    description = "Deleted task from database",
                    content = {@Content(mediaType = "application/json")})
                })
        @DeleteMapping("/tasks/{taskId}")
        public String deleteTasks(@PathVariable Long taskId) {
            TaskDto taskDto = taskService.findById(taskId);
            if (taskDto == null)
                throw new NotFoundException("Task id not found - " + taskId);
            taskService.deleteById(taskId);
            return "Deleted task with id - " + taskId;
        }


}
