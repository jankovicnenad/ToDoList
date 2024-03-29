package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.PriorityDtoResponse;
import com.example.ToDoList.DTO.PriorityDtoRequest;
import com.example.ToDoList.service.PriorityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
@RequestMapping("/api")
public class PriorityRestController {

    private PriorityServiceImpl priorityService;

    public PriorityRestController(PriorityServiceImpl theService)
        {
            priorityService = theService;
        }

    @Operation(summary = "Get all priorities from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched all priorities from database")})
    @GetMapping("/priority")
    public List<PriorityDtoResponse> getAllPriority(){return priorityService.getAllPriority();}

    @Operation(summary = "Get priority from database by specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Fetched priority from database by specific ID")})
    @GetMapping("/priority/{priorityId}")
    public PriorityDtoResponse findById(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        return priorityDtoResponse;
    }

    @Operation(summary = "Insert priority to database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Inserted priority to database")})
    @PostMapping("/priority")
    public PriorityDtoResponse addPriority(@RequestBody PriorityDtoRequest thePriority){
        return priorityService.savePriority(thePriority);

    }

    @Operation(summary = "Update priority in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Updated priority in database")})
    @PutMapping("/priority")
    public ResponseEntity<?> updatePriority(@RequestBody PriorityDtoRequest priorityDtoRequest){
        return new ResponseEntity<>(priorityService.updatePriority(priorityDtoRequest), HttpStatus.OK) ;

    }

    @Operation(summary = "Delete priority from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Deleted priority from database")})
    @DeleteMapping("/priority/{priorityId}")
    public ResponseEntity<?> deletePriority(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        priorityService.delete(priorityId);
        return new ResponseEntity<>(Collections.singletonMap("message", "Deleted priority with id: " + priorityId), HttpStatus.OK);
    }
}
