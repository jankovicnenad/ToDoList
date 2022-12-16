package com.example.ToDoList.service;

import com.example.ToDoList.DAO.ImageRepository;
import com.example.ToDoList.DTO.CommentDto;
import com.example.ToDoList.DTO.ImageDTO;
import com.example.ToDoList.DTO.TaskDto;
import com.example.ToDoList.entity.Comment;
import com.example.ToDoList.entity.Image;
import com.example.ToDoList.entity.Task;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    public ImageServiceImpl (ImageRepository imgRepo)
    {
        imageRepository = imgRepo;
    }

    public Image convertImageDtoToImage(ImageDTO imageDTO){

        Image image = new Image();
        image.setId(imageDTO.getId());
        image.setDescription(imageDTO.getDescription());
        Task task = new Task();
        task.setId(imageDTO.getTask_dto().getId());
        image.setTask(task);
        return image;
    }
    public ImageDTO convertImageToImageDto(Image image){
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setDescription(image.getDescription());
        TaskDto taskDto = new TaskDto();
        taskDto.setId(image.getTask().getId());
        imageDTO.setTask_dto(taskDto);
        return  imageDTO;
    }

    @Override
    public List<ImageDTO> getAllImage() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> iDTO = new ArrayList<>();
        for(Image i : images)
        {
           ImageDTO imageDTOS = convertImageToImageDto(i);
           iDTO.add(imageDTOS);
        }
       return iDTO;

    }

    @Override
    public ImageDTO findById(int id) {
        Optional<Image> image = Optional.ofNullable(imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id is not found - " + id)));
        ImageDTO imageDTO = convertImageToImageDto(image.get());
        return imageDTO;
    }

    @Override
    public void save(ImageDTO imageDTO) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id, ImageDTO imageDTO) {

    }
}
