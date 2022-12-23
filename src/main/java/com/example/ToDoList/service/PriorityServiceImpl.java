package com.example.ToDoList.service;

import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.entity.Priority;
import com.example.ToDoList.rest.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PriorityServiceImpl implements PriorityService{

    private final PriorityRepository priorityRepository;

    private final MapperDto mapperDto;


    public PriorityServiceImpl(PriorityRepository thePriority, MapperDto mapperDto)
    {
        priorityRepository = thePriority;
        this.mapperDto = mapperDto;
    }

    @Override
    public List<PriorityDto> getAllPriority() {
        List<Priority> priorities = priorityRepository.findAll();
        List<PriorityDto> pDto = new ArrayList<>();
        for(Priority p : priorities)
        {
            PriorityDto priorityDto = mapperDto.convertPriorityToPriorityDto(p);
            pDto.add(priorityDto);
        }


        return pDto;
    }

    @Override
    public PriorityDto findById(Long id) {
        Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
       PriorityDto priorityDto = mapperDto.convertPriorityToPriorityDto(priority.get());
       return priorityDto;
    }

    @Override
    public void savePriority(PriorityDto priorityDto) {
        Priority p = mapperDto.convertPriorityDtoToPriority(priorityDto);
        priorityRepository.save(p);
        priorityDto.setId(p.getId());
    }

    @Override
    public void delete(Long id) {
        Optional<Priority> priority = Optional.ofNullable(priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Priority id not found - " + id)));
        priorityRepository.delete(priority.get());
    }

    @Override
    public void updatePriority(Long id, PriorityDto priorityDto) {
        Priority priority = priorityRepository.findById(id).orElseThrow(() -> new NotFoundException("Task id is not found - " + id));
        Priority priority1 = mapperDto.convertPriorityDtoToPriority(priorityDto);
        priority1.setId(priority.getId());
        priorityRepository.save(priority1);
    }

}
