package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.service.StatusServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusRestController {

    private StatusServiceImpl statusService;

    public StatusRestController(StatusServiceImpl theService)
    {
        statusService = theService;
    }

    @GetMapping("/status")
    public List<StatusDto> getAllStatus(){
        return statusService.getAllStatus();
    }
    @PostMapping("/status")
    public StatusDto addStatus(@RequestBody StatusDto theStatus){
        theStatus.setId(0);
        statusService.save(theStatus);
        return theStatus;
    }
}
