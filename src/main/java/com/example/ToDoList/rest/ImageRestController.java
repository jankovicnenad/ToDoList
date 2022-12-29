package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.ImageDTO;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.service.ImageService;
import com.example.ToDoList.service.TaskService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @PostMapping("/tasks")
//    public String uploadFile(@RequestPart("files") MultipartFile multipartFile, @RequestPart TaskDto taskDto) throws Exception {
//        if(multipartFile.isEmpty())
//         taskService.save(taskDto);
//       else {
//            if (!multipartFile.getOriginalFilename().endsWith(".jpg") && !multipartFile.getOriginalFilename().endsWith(".png")) {
//                // Ako nije, vratite gresku
//                return "Invalid file extension";
//            }
//           System.out.println(imageService.uploadFile(multipartFile));
//        taskService.saveImage(taskDto, multipartFile);}
//        return "Uspesno odradjena metoda!";
//}}
