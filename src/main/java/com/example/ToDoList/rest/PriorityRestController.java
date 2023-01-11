package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.PriorityDtoResponse;
import com.example.ToDoList.DTO.PriorityDtoRequest;
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
    public List<PriorityDtoResponse> getAllPriority(){return priorityService.getAllPriority();}

    @GetMapping("/priority/{priorityId}")
    public PriorityDtoResponse findById(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        return priorityDtoResponse;
    }

    @PostMapping("/priority")
    public PriorityDtoResponse addPriority(@RequestBody PriorityDtoRequest thePriority){
        return priorityService.savePriority(thePriority);

    }
    @PutMapping("/priority")
    public String updatePriority(@RequestBody PriorityDtoRequest priorityDtoRequest){
        Long priorityId = priorityDtoRequest.getId();
        priorityService.updatePriority(priorityId, priorityDtoRequest);
        return "Updated priority with id - " +priorityId;
    }
    @DeleteMapping("/priority/{priorityId}")
    public String deletePriority(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        priorityService.delete(priorityId);
        return "Deleted priority with id - " +priorityId;
    }
}
