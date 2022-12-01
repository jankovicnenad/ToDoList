package com.example.ToDoList.service;

import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.entity.Priority;

import java.util.List;

public interface PriorityService {

    public List<PriorityDto> getAllPriority();

    public PriorityDto findById(int id);

    public void savePriority(PriorityDto priorityDto);

}
