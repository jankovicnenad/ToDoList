package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDtoResponse;
import com.example.ToDoList.DTO.StatusDtoRequest;
import com.example.ToDoList.service.StatusServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusRestController {

    private StatusServiceImpl statusService;

    public StatusRestController(StatusServiceImpl theService) {
        statusService = theService;
    }

    @Operation(summary = "Get all statuses from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All statuses are extracted from database", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/status")
    public List<StatusDtoResponse> getAllStatus() {
        return statusService.getAllStatus();
    }

    @Operation(summary = "Get status from database with specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched status with specific ID", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/status/{statusId}")
    public StatusDtoResponse findById(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null)
            throw new NotFoundException("Status id is not found - " + statusId);
        return statusDtoResponse;
    }

    @Operation(summary = "Save status in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Saved status in the database", content = {@Content(mediaType = "application/json")})})
    @PostMapping("/status")
    public StatusDtoResponse saveStatus(@RequestBody StatusDtoRequest theStatus) {
        return statusService.save(theStatus);

    }

    @Operation(summary = "Update status in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched status with specific ID", content = {@Content(mediaType = "application/json")})})
    @PutMapping("/status")
    public String updateStatus(@RequestBody StatusDtoRequest statusDto) {
        Long statusId = statusDto.getId();
        statusService.updateStatus(statusId, statusDto);
        return "Update status with id - " + statusId;
    }

    @Operation(summary = "Delete status from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted status from database", content = {@Content(mediaType = "application/json")})})
    @DeleteMapping("/status/{statusId}")
    public String deleteStatus(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null)
            throw new NotFoundException("Status id not found - " + statusId);
        statusService.delete(statusId);
        return "Deleted status with id - " + statusId;
    }
}
