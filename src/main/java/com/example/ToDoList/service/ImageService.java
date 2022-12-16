package com.example.ToDoList.service;

import com.example.ToDoList.DTO.ImageDTO;
import com.example.ToDoList.entity.Image;

import java.util.List;

public interface ImageService {

    public List<ImageDTO> getAllImage();

    public ImageDTO findById(int id);

    public void save(ImageDTO imageDTO);

    public void delete(int id);

    public void update(int id, ImageDTO imageDTO);
}
