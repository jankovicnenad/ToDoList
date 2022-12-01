package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.service.TaskService;
import com.example.ToDoList.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "localhost:8080")
@RestController
@RequestMapping("/api")
public class TaskRestController {

        private List<Task> tasks;
        private TaskServiceImpl taskService;

        private TaskRepository taskRepository;

        @Autowired
        public TaskRestController(TaskServiceImpl TheTask, TaskRepository theRepo)
        {
            taskService = TheTask;
            taskRepository = theRepo;
        }

        @GetMapping("/tasks")
        public List<TaskDto> getAllTasks(){
            return taskService.getAllTasks();
        }

       /* @GetMapping("/tasks/{taskId}")
        public Optional<Task> getTasks(@PathVariable int taskId){
            Optional<Task> theTask = taskService.findById(taskId);

            if(theTask == null){
                throw new RuntimeException("Task id not found: " +taskId);
            }
            return theTask;
        }*/

        @PostMapping("/tasks")
        public TaskDto addTask(@RequestBody TaskDto theTask){


            return taskService.save(theTask);
        }
        @PutMapping("/tasks")
        public TaskDto updateTask(@RequestBody TaskDto taskDto){
            return taskService.save(taskDto);
        }

        @GetMapping("/tasks/{taskId}")
        public TaskDto getTasks(@PathVariable int taskId){

            return taskService.findById(taskId);
//
        }

        @DeleteMapping("/tasks/{taskId}")
        public void deleteTasks(@PathVariable int taskId){
            taskService.deleteById(taskId);
        }


}
