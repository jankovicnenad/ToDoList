package com.example.ToDoList.service;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;

import java.util.List;

public interface StatusService {

    public List<StatusDto> getAllStatus();

    public void save(StatusDto statusDto);
}
