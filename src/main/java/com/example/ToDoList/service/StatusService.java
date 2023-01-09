package com.example.ToDoList.service;

import com.example.ToDoList.DTO.StatusDtoResponse;
import com.example.ToDoList.DTO.StatusDtoRequest;

import java.util.List;

public interface StatusService {

    public List<StatusDtoResponse> getAllStatus();

    public StatusDtoResponse findById(Long id);

    public StatusDtoResponse save(StatusDtoRequest statusDto);

    public void delete(Long id);

    void updateStatus(Long id, StatusDtoRequest statusDto);
}
