package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskRestController {

        private List<Task> tasks;
        private TaskService taskService;

        @Autowired
        public TaskRestController(TaskService TheTask)
        {
            taskService = TheTask;
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
        @PostMapping("/task")
        public TaskDto addTask(@RequestBody TaskDto theTask){


            return taskService.save(theTask);
        }

        @GetMapping("/tasks/{taskId}")
        public Task getTasks(@PathVariable int taskId){
         if((taskId >= tasks.size())|| (taskId<0)){
             throw new TaskNotFoundException("Task id not found - " +taskId);
         }
         return tasks.get(taskId);
        }
        /*@DeleteMapping("/home/{taskId}")
        public String deleteTask(@PathVariable int taskId)
            {
                Task tempTask = taskService.findById(taskId);

                if(tempTask == null)
                {throw new RuntimeException("Task id not found - " +taskId);}
                taskService.deleteById(taskId);
                return "Delete task id - " +taskId;
            }*/
}
