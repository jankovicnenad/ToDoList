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


    private StatusDto convertStatusToStatusDto(Status status){

        StatusDto statustDto = new StatusDto();
        statustDto.setId(status.getId());
        statustDto.setStatus_name(status.getStatus());
        return statustDto;
    }
    private Status convertStatusDtoToStatus(StatusDto statusD){

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
            StatusDto statDto = convertStatusToStatusDto(s);
            sDto.add(statDto);
        }
        return sDto;
    }

    @Override
    public StatusDto findById(int id) {
        Optional<Status> status = statusRepository.findById(id);
        StatusDto statusDto = convertStatusToStatusDto(status.get());

        return statusDto;
    }

    /*@Override
    public List<StatusDto> findById(@PathVariable int id) {
        Optional<Status> statList = statusRepository.findById(id);
        List<StatusDto> sDto = new ArrayList<>();
        for(Status s : statList)
        {StatusDto statusDto = statusD(s);
        sDto.add(statusDto);
        return sDto;
        }
    }*/
    @Override
    public void save(StatusDto statusDto) {
    Status s = convertStatusDtoToStatus(statusDto);
    statusRepository.save(s);
    }

    @Override
    public void delete(int id) {
        Optional<Status> status = statusRepository.findById(id);
        statusRepository.delete(status.get());
    }
}
