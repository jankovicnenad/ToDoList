package com.example.ToDoList.rest;

import com.example.ToDoList.service.ImageServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
public class ImageRestController {

    ImageServiceImpl imageService;

    public ImageRestController(ImageServiceImpl imageService){this.imageService = imageService;}

    @PostMapping("/post-image")
    public String uploadFile(@RequestParam("files") MultipartFile multipartFile) throws Exception {
        imageService.uploadFile(multipartFile);
        return "Uspesno odradjena metoda!";
}}
