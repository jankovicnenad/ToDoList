package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.service.ImageService;
import com.example.ToDoList.service.TaskService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
@RestController
public class ImageRestController {

    private final ImageService imageService;
    private final TaskService taskService;

    public ImageRestController(ImageService imageService, TaskService taskService){this.imageService = imageService;
        this.taskService = taskService;
    }

    @PostMapping("/post-image")
    public String uploadFile(@RequestPart("files") MultipartFile multipartFile, @RequestPart TaskDto taskDto) throws Exception {
        System.out.println(imageService.uploadFile(multipartFile));
        taskService.save(taskDto, multipartFile);
        return "Uspesno odradjena metoda!";
}}
