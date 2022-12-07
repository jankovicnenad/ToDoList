package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDto;
import com.example.ToDoList.entity.Status;
import com.example.ToDoList.service.StatusServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "localhost:8080")
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

        StatusDto statusDto = statusService.findById(statusId);
        if(statusDto == null)
        throw new NotFoundException("Status id is not found - " +statusId);
        return statusDto;
    }
    @PostMapping("/status")
    public StatusDto addStatus(@RequestBody StatusDto theStatus){
        statusService.save(theStatus);
        return theStatus;
    }
    @PutMapping("/status/{statusId}")
    public void updateStatus(@PathVariable int statusId, @RequestBody StatusDto statusDto){
        statusService.updateStatus(statusId, statusDto);
    }
    @DeleteMapping("/status/{statusId}")
    public void deleteStatus(@PathVariable int statusId){

        StatusDto statusDto = statusService.findById(statusId);
        if(statusDto == null)
            throw new NotFoundException("Status id not found - " +statusId);
        statusService.delete(statusId);
    }
}
