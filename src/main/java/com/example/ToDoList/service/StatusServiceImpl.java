package com.example.ToDoList.service;

import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService{

    private StatusRepository statusRepository;

    private final MapperDto mapperDto;
    public StatusServiceImpl(StatusRepository theStatus, MapperDto mapperDto) {
        this.statusRepository = theStatus;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<StatusDto> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        List <StatusDto> sDto = new ArrayList<>();
        for (Status s : statusList){
            StatusDto statDto = mapperDto.convertStatusToStatusDto(s);
            sDto.add(statDto);
        }
        return sDto;
    }

    @Override
    public StatusDto findById(Long id) {
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status id not found - " + id)));
        StatusDto statusDto = mapperDto.convertStatusToStatusDto(status.get());

        return statusDto;
    }
    @Override
    public void save(StatusDto statusDto) {
    Status s = mapperDto.convertStatusDtoToStatus(statusDto);
    statusRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        Optional<Status> status = Optional.ofNullable(statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Status id not found - " + id)));
        statusRepository.delete(status.get());
    }
    @Override
    public void updateStatus(Long id, StatusDto statusDto) {
        Status status = statusRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Status status1 = mapperDto.convertStatusDtoToStatus(statusDto);
        status1.setId(status.getId(1));
        statusRepository.save(status1);
    }
}
