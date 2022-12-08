package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "localhost:8080")
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
        @PutMapping("/tasks/{taskId}")
        public String updateTask(@PathVariable int taskId, @RequestBody TaskDto taskDto){
            taskService.updateTask(taskId,taskDto);
            return "Updated task with id - " +taskId;
        }

        @GetMapping("/tasks/{taskId}")
        public TaskDto getTasks(@PathVariable int taskId){
            TaskDto taskDto = taskService.findById(taskId);
            if (taskDto == null){
                throw new NotFoundException("Task id not found - " +taskId);
            }
            return taskDto;
        }

        @DeleteMapping("/tasks/{taskId}")
        public String deleteTasks(@PathVariable int taskId){
            TaskDto taskDto = taskService.findById(taskId);
            if(taskDto == null)
                throw new NotFoundException("Task id not found - " +taskId);
            taskService.deleteById(taskId);
            return "Deleted task with id - " +taskId;
        }


}
