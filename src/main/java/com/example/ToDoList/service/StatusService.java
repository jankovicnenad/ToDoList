package com.example.ToDoList.service;

import com.example.ToDoList.DTO.StatusDto;

import java.util.List;

public interface StatusService {

    public List<StatusDto> getAllStatus();

    public StatusDto findById(Long id);

    public void save(StatusDto statusDto);

    public void delete(Long id);

    void updateStatus(Long id, StatusDto statusDto);
}
