package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.ImageDTO;
import com.example.ToDoList.service.ImageService;
import com.example.ToDoList.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ImageRestController {

    private final ImageService imageService;
    private final TaskService taskService;

    public ImageRestController(ImageService imageService, TaskService taskService){this.imageService = imageService;
        this.taskService = taskService;
    }

    @GetMapping("/image")
    public List<ImageDTO> getImages(){
        return imageService.getAllImage();
    }
}


