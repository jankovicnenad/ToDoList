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

    @PostMapping("/priority")
    public PriorityDto addPriority(@RequestBody PriorityDto thePriority){
        priorityService.savePriority(thePriority);
        return thePriority;
    }
}
