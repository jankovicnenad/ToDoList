package com.example.ToDoList.service;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;

import java.util.List;
import java.util.Optional;

public interface StatusService {

    public List<StatusDto> getAllStatus();

    public StatusDto findById(int id);

    public void save(StatusDto statusDto);
}
