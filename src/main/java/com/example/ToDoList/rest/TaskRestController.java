package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskRestController {

    private TaskService taskService;

    @Autowired
    public TaskRestController(TaskService TheTask)
    {
        taskService = TheTask;
    }
    @GetMapping("/tasks")
    public List<Task> findAll(){
        return taskService.findAll();
    }

    @PostMapping("/task")
    public TaskDto addTask(@RequestBody TaskDto theTask){
        taskService.save(theTask);

        return theTask;
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
