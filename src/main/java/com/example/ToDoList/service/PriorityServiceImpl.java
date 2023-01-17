package com.example.ToDoList.service;

import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DTO.PriorityDtoResponse;
import com.example.ToDoList.DTO.PriorityDtoRequest;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.rest.BadRequestException;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;

    private final MapperDto mapperDto;


    public PriorityServiceImpl(PriorityRepository thePriority, MapperDto mapperDto) {
        priorityRepository = thePriority;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<PriorityDtoResponse> getAllPriority() {
        try {
            List<Priority> priorities = priorityRepository.findAll();
            List<PriorityDtoResponse> pDto = new ArrayList<>();
            for (Priority p : priorities) {
                PriorityDtoResponse priorityDtoResponse = mapperDto.convertPriorityToPriorityDtoResponse(p);
                pDto.add(priorityDtoResponse);
            }


            return pDto;
        } catch (Exception e) {
            throw new BadRequestException("Invalid request!");
        }
    }

    @Override
    public PriorityDtoResponse findById(Long id) {
        try {
            Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
            PriorityDtoResponse priorityDtoResponse = mapperDto.convertPriorityToPriorityDtoResponse(priority.get());
            return priorityDtoResponse;
        } catch (Exception e) {
            throw new BadRequestException("Invalid request!");
        }
    }

    @Override
    public PriorityDtoResponse savePriority(PriorityDtoRequest priorityDtoRequest) {
        try {
            Priority priority = mapperDto.convertPriorityDtoRequestToPriority(priorityDtoRequest);
            priorityRepository.save(priority);
            return mapperDto.convertPriorityToPriorityDtoResponse(priority);
        } catch (Exception e) {
            throw new BadRequestException("Invalid request!");
        }

    }

    @Override
    public void delete(Long id) {
        try {
            Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
            priorityRepository.delete(priority.get());
        } catch (Exception e) {
            throw new BadRequestException("Invalid request!");
        }
    }

    @Override
    public PriorityDtoResponse updatePriority(PriorityDtoRequest priorityDtoRequest) {
        try {
            Priority priority = priorityRepository.findById(priorityDtoRequest.getId()).orElseThrow(() -> new NotFoundException("Task id is not found - " + priorityDtoRequest.getId()));
            priority.setPriority_name(priorityDtoRequest.getPriority_name());
            return mapperDto.convertPriorityToPriorityDtoResponse(priorityRepository.save(priority));
        } catch (Exception e) {
            throw new BadRequestException("Invalid request!");
        }

    }

}
