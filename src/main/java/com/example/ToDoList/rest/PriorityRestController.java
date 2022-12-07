package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.service.PriorityService;
import com.example.ToDoList.service.PriorityServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PriorityRestController {

    private PriorityServiceImpl priorityService;

    public PriorityRestController(PriorityServiceImpl theService)
        {
            priorityService = theService;
        }
    @GetMapping("/priority")
    public List<PriorityDto> getAllPriority(){return priorityService.getAllPriority();}

    @GetMapping("/priority/{priorityId}")
    public PriorityDto findById(@PathVariable int priorityId){

        PriorityDto priorityDto = priorityService.findById(priorityId);
        if(priorityDto == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        return priorityDto;
    }

    @PostMapping("/priority")
    public PriorityDto addPriority(@RequestBody PriorityDto thePriority){
        priorityService.savePriority(thePriority);
        return thePriority;
    }
    @PutMapping("/priority/{priorityId}")
    public void updatePriority(@PathVariable int priorityId, @RequestBody PriorityDto priorityDto){
        priorityService.updatePriority(priorityId, priorityDto);
    }
    @DeleteMapping("/priority/{priorityId}")
    public void deletePriority(@PathVariable int priorityId){

        PriorityDto priorityDto = priorityService.findById(priorityId);
        if(priorityDto == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        priorityService.delete(priorityId);
    }
}
