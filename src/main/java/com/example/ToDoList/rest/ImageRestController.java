package com.example.ToDoList.rest;

import com.example.ToDoList.service.ImageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
public class ImageRestController {

    ImageServiceImpl imageService;

    public ImageRestController(ImageServiceImpl imageService){this.imageService = imageService;}

    @PostMapping("/post-image")
    public String uploadFile(@RequestPart("files") MultipartFile multipartFile) throws Exception {
        System.out.println(imageService.uploadFile(multipartFile));
        return "Uspesno odradjena metoda!";
}}
