package com.example.ToDoList.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
public class ImageRestController {
    @PostMapping("/download-file")
    public ResponseEntity<Object> downloadFile(@RequestBody FileDTO fileDTO, HttpServletRequest request) throws Exception {
        return storageService.downloadFile(fileDTO.getFileDownloadUri(), request);
}
