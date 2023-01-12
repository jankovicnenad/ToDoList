package com.example.ToDoList.service;

import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DTO.StatusDtoResponse;
import com.example.ToDoList.DTO.StatusDtoRequest;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private StatusRepository statusRepository;

    private final MapperDto mapperDto;

    public StatusServiceImpl(StatusRepository theStatus, MapperDto mapperDto) {
        this.statusRepository = theStatus;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<StatusDtoResponse> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        List<StatusDtoResponse> sDto = new ArrayList<>();
        for (Status s : statusList) {
            StatusDtoResponse statDto = mapperDto.convertStatusToStatusDtoResponse(s);
            sDto.add(statDto);
        }
        return sDto;
    }

    @Override
    public StatusDtoResponse findById(Long id) {
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status id not found - " + id)));
        StatusDtoResponse statusDtoResponse = mapperDto.convertStatusToStatusDtoResponse(status.get());

        return statusDtoResponse;
    }

    @Override
    public StatusDtoResponse save(StatusDtoRequest statusDtoRequest) {
        Status status = mapperDto.convertStatusDtoRequestToStatus(statusDtoRequest);
        statusRepository.save(status);
        return mapperDto.convertStatusToStatusDtoResponse(status);
    }

    @Override
    public void delete(Long id) {
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status id not found - " + id)));
        statusRepository.delete(status.get());
    }

    @Override
    public StatusDtoResponse updateStatus(StatusDtoRequest statusDtoRequest) {
        Status status = statusRepository.findById(statusDtoRequest.getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + statusDtoRequest.getStatus_name()));
        status.setStatus_name(statusDtoRequest.getStatus_name());
         return mapperDto.convertStatusToStatusDtoResponse(statusRepository.save(status));
    }
}
