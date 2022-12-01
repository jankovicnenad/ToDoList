package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;
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
    @GetMapping("/status/{statusId}")
    public StatusDto findById(@PathVariable int statusId){
        return statusService.findById(statusId);
    }
    @PostMapping("/status")
    public StatusDto addStatus(@RequestBody StatusDto theStatus){
        statusService.save(theStatus);
        return theStatus;
    }
    @PutMapping("/status")
    public StatusDto updateStatus(@RequestBody StatusDto statusDto){
        statusService.save(statusDto);
        return statusDto;
    }
    @DeleteMapping("/status/{statusId}")
    public void deleteStatus(@PathVariable int statusId){
        statusService.delete(statusId);
    }
}
