package com.example.ToDoList.service;

import com.example.ToDoList.DAO.PriorityRepository;
import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.entity.Priority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PriorityServiceImpl implements PriorityService{

    private PriorityRepository priorityRepository;

    public PriorityServiceImpl(PriorityRepository thePriority)
    {
        priorityRepository = thePriority;
    }

    private PriorityDto priorityD(Priority priority){
        PriorityDto priorityDto = new PriorityDto();
        priorityDto.setId(priority.getId());
        priorityDto.setPriority(priority.getPriority());
        return priorityDto;
    }
    private Priority priorityE(PriorityDto priorityDto){
        Priority priority = new Priority();
        priority.setId(priorityDto.getId());
        priority.setPriority(priorityDto.getPriority());
        return priority;
    }


    @Override
    public List<PriorityDto> getAllPriority() {
        List<Priority> priorities = priorityRepository.findAll();
        List<PriorityDto> pDto = new ArrayList<>();
        for(Priority p : priorities)
        {
            PriorityDto priorityDto = priorityD(p);
            pDto.add(priorityDto);
        }


        return pDto;
    }

    @Override
    public void savePriority(PriorityDto priorityDto) {
        Priority p = priorityE(priorityDto);
        priorityRepository.save(p);
    }
}
