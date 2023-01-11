package com.example.ToDoList.rest;

import com.example.ToDoList.DTO.PriorityDtoResponse;
import com.example.ToDoList.DTO.PriorityDtoRequest;
import com.example.ToDoList.service.PriorityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all priorities from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get all priorities from database", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/priority")
    public List<PriorityDtoResponse> getAllPriority(){return priorityService.getAllPriority();}

    @Operation(summary = "Get priority from database by specific ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Get priority from database by specific ID", content = {@Content(mediaType = "application/json")})})
    @GetMapping("/priority/{priorityId}")
    public PriorityDtoResponse findById(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        return priorityDtoResponse;
    }

    @Operation(summary = "Insert priority to database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Insert priority to database", content = {@Content(mediaType = "application/json")})})
    @PostMapping("/priority")
    public PriorityDtoResponse addPriority(@RequestBody PriorityDtoRequest thePriority){
        return priorityService.savePriority(thePriority);

    }

    @Operation(summary = "Update priority in database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Update priority in database", content = {@Content(mediaType = "application/json")})})
    @PutMapping("/priority")
    public String updatePriority(@RequestBody PriorityDtoRequest priorityDtoRequest){
        Long priorityId = priorityDtoRequest.getId();
        priorityService.updatePriority(priorityId, priorityDtoRequest);
        return "Updated priority with id - " +priorityId;
    }

    @Operation(summary = "Delete priority from database")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Delete priority from database", content = {@Content(mediaType = "application/json")})})
    @DeleteMapping("/priority/{priorityId}")
    public String deletePriority(@PathVariable Long priorityId){

        PriorityDtoResponse priorityDtoResponse = priorityService.findById(priorityId);
        if(priorityDtoResponse == null)
            throw new NotFoundException("Priority id not found - " +priorityId);
        priorityService.delete(priorityId);
        return "Deleted priority with id - " +priorityId;
    }
}
