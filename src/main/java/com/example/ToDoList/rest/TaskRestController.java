package com.example.ToDoList.rest;

import com.example.ToDoList.DAO.TaskRepository;
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
    @GetMapping("/home")
    public List<Task> findAll(){
        return taskService.finfAll();
    }

    @PutMapping("/home")
    public Task addTask(@RequestBody Task theTask){
        theTask.setId(0);

        taskService.save(theTask);

        return theTask;
    }
}
