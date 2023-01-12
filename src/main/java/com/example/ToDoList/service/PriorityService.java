package com.example.ToDoList.service;

import com.example.ToDoList.DTO.PriorityDtoResponse;
import com.example.ToDoList.DTO.PriorityDtoRequest;

import java.util.List;

public interface PriorityService {

    public List<PriorityDtoResponse> getAllPriority();

    public PriorityDtoResponse findById(Long id);

    public PriorityDtoResponse savePriority(PriorityDtoRequest priorityDto);

    public void delete(Long id);

    public void updatePriority(Long id, PriorityDtoRequest priorityDtoResponse);


}
