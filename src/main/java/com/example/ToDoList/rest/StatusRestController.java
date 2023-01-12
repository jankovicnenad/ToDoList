package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.StatusDtoResponse;
import com.example.ToDoList.DTO.StatusDtoRequest;
import com.example.ToDoList.service.StatusServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StatusRestController {

    private StatusServiceImpl statusService;

    public StatusRestController(StatusServiceImpl theService) {
        statusService = theService;
    }

    @Operation(summary = "Get all statuses from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "All statuses are extracted from database")})
    @GetMapping("/status")
    public List<StatusDtoResponse> getAllStatus() {
        return statusService.getAllStatus();
    }

    @Operation(summary = "Get status from database with specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched status with specific ID")})
    @GetMapping("/status/{statusId}")
    public StatusDtoResponse findById(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null)
            throw new NotFoundException("Status id is not found - " + statusId);
        return statusDtoResponse;
    }

    @Operation(summary = "Save status in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Saved status in the database")})
    @PostMapping("/status")
    public StatusDtoResponse saveStatus(@RequestBody StatusDtoRequest theStatus) {
        return statusService.save(theStatus);

    }

    @Operation(summary = "Update status in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched status with specific ID")})
    @PutMapping("/status")
    public ResponseEntity<?> updateStatus(@RequestBody StatusDtoRequest statusDto) {
        Long statusId = statusDto.getId();
        return new ResponseEntity<>(statusService.updateStatus(statusDto), HttpStatus.OK);
    }

    @Operation(summary = "Delete status from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted status from database")})
    @DeleteMapping("/status/{statusId}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long statusId) {

        StatusDtoResponse statusDtoResponse = statusService.findById(statusId);
        if (statusDtoResponse == null){
            throw new NotFoundException("Status id not found - " + statusId);
        }
        statusService.delete(statusId);
        return new ResponseEntity<>(Collections.singletonMap("message", "deleted status with id: " + statusId), HttpStatus.OK );
    }
}
