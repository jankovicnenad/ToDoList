package com.example.ToDoList.service;

import com.example.ToDoList.DTO.PriorityDto;

import java.util.List;

public interface PriorityService {

    public List<PriorityDto> getAllPriority();

    public PriorityDto findById(Long id);

    public void savePriority(PriorityDto priorityDto);

    public void delete(Long id);

    public void updatePriority(Long id, PriorityDto priorityDto);


}
