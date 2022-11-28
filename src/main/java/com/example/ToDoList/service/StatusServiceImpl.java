package com.example.ToDoList.service;

import com.example.ToDoList.DAO.StatusRepository;
import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService{

    private StatusRepository statusRepository;
    public StatusServiceImpl(StatusRepository theStatus) {
        this.statusRepository = theStatus;
    }


    private StatusDto statusD(Status status){

        StatusDto statustDto = new StatusDto();
        statustDto.setId(status.getId());
        statustDto.setStatus_name(status.getStatus());
        return statustDto;
    }
    private Status status(StatusDto statusD){

        Status status = new Status();
        status.setId(statusD.getId());
        status.setStatus(statusD.getStatus_name());
        return status;
    }

    @Override
    public List<StatusDto> getAllStatus() {
        List<Status> statusList = statusRepository.findAll();
        List <StatusDto> sDto = new ArrayList<>();
        for (Status s : statusList){
            StatusDto statDto = statusD(s);
            sDto.add(statDto);
        }
        return sDto;
    }

    @Override
    public List<StatusDto> getById(int id) {
        return null;
    }


    @Override
    public void save(StatusDto statusDto) {
    Status s = status(statusDto);
    statusRepository.save(s);
    }
}
