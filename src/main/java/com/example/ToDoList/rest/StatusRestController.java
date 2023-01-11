package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDtoResponse;
import com.example.ToDoList.DTO.StatusDtoRequest;
import com.example.ToDoList.service.StatusServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusRestController {

    private StatusServiceImpl statusService;

    public StatusRestController(StatusServiceImpl theService) {
        statusService = theService;
    }

    @GetMapping("/status")
    public List<StatusDtoResponse> getAllStatus() {
        return statusService.getAllStatus();
    }

    @GetMapping("/status/{statusId}")
    public StatusDtoResponse findById(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null)
            throw new NotFoundException("Status id is not found - " + statusId);
        return statusDtoResponse;
    }

    @PostMapping("/status")
    public StatusDtoRequest addStatus(@RequestBody StatusDtoRequest theStatus) {
        statusService.save(theStatus);
        return theStatus;
    }

    @PutMapping("/status")
    public String updateStatus(@RequestBody StatusDtoRequest statusDto) {
        Long statusId = statusDto.getId();
        statusService.updateStatus(statusId, statusDto);
        return "Update status with id - " + statusId;
    }

    @DeleteMapping("/status/{statusId}")
    public String deleteStatus(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null)
            throw new NotFoundException("Status id not found - " + statusId);
        statusService.delete(statusId);
        return "Deleted status with id - " + statusId;
    }
}
