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
    public StatusDtoResponse save(StatusDtoRequest statusDto) {
        Status s = mapperDto.convertStatusDtoRequestToStatus(statusDto);
        statusRepository.save(s);
        return mapperDto.convertStatusToStatusDtoResponse(s);
    }

    @Override
    public void delete(Long id) {
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status id not found - " + id)));
        statusRepository.delete(status.get());
    }

    @Override
    public void updateStatus(Long id, StatusDtoRequest statusDto) {
        Status status = statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Status status1 = mapperDto.convertStatusDtoRequestToStatus(statusDto);
        status1.setId(status.getId());
        statusRepository.save(status1);
    }
}
