package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.service.TaskServiceImpl;
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

        @PostMapping("/tasks")
        public TaskDto addTasks(@RequestBody TaskDto task){

            return taskService.save(task);
        }
        @GetMapping("/tasks")
        public List<TaskDto> getAllTasks(@RequestParam(required = false, name = "priority") Long priorityId, @RequestParam(required = false, name = "status") Long statusId){
            return taskService.getAllTasks(priorityId, statusId);
        }
        @PutMapping("/tasks/{taskId}")
        public String updateTask(@PathVariable Long taskId, @RequestBody TaskDto taskDto) throws IOException {
            taskService.updateTask(taskId,taskDto);
            return "Updated task with id - " +taskId;
        }

        @GetMapping("/tasks/{taskId}")
        public TaskDto getTasks(@PathVariable Long taskId){
            TaskDto taskDto = taskService.findById(taskId);
            if (taskDto == null){
                throw new NotFoundException("Task id not found - " +taskId);
            }
            return taskDto;
        }

        @DeleteMapping("/tasks/{taskId}")
        public String deleteTasks(@PathVariable Long taskId) {
            TaskDto taskDto = taskService.findById(taskId);
            if (taskDto == null)
                throw new NotFoundException("Task id not found - " + taskId);
            taskService.deleteById(taskId);
            return "Deleted task with id - " + taskId;
        }


}
