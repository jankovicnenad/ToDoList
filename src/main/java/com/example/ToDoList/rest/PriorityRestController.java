package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.PriorityDto;
import com.example.ToDoList.service.PriorityServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin(origins = "192.168.0.105")
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
    public PriorityDto findById(@PathVariable Long priorityId){

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
    public String updatePriority(@PathVariable Long priorityId, @RequestBody PriorityDto priorityDto){
        priorityService.updatePriority(priorityId, priorityDto);
        return "Updated priority with id - " +priorityId;
    }
    @DeleteMapping("/priority/{priorityId}")
    public String deletePriority(@PathVariable Long priorityId){

        PriorityDto priorityDto = priorityService.findById(priorityId);
        if(priorityDto == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        priorityService.delete(priorityId);
        return "Deleted priority with id - " +priorityId;
    }
}
