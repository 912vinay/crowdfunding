package com.crowdfunding.api.controller;

import com.crowdfunding.api.services.ProjectFundingRequestService;
import com.crowdfunding.api.services.ProjectService;
import com.crowdfunding.dto.ProjectFundingRequestDto;
import com.crowdfunding.entities.ProjectFundingRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/fundingRequest")
@AllArgsConstructor
@Validated
@Slf4j
public class ProjectFundingRequestController {

    private final ProjectFundingRequestService projectFundingRequestService;
    private final ProjectService projectService;

    @PostMapping()
    public ResponseEntity<ProjectFundingRequest> fundingRequest(@RequestBody ProjectFundingRequestDto projectFundingRequestDto) {
            ProjectFundingRequest projectFundingRequest = projectFundingRequestService.fundingRequest(projectFundingRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(projectFundingRequest);
    }

    // Get all projects
    @GetMapping("/all")
    public List<ProjectFundingRequest> getAllProjectFundingRequest() {
        return projectFundingRequestService.getAllProjectFundingRequest();
    }

    // Get a project by ID
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Set<ProjectFundingRequest>> getAllFundingRequestByProjectId(@PathVariable Long projectId) {
        Set<ProjectFundingRequest> allFundingRequestByProject = projectService.getProjectFundingRequestByProjectId(projectId);
        return new ResponseEntity<>(allFundingRequestByProject, HttpStatusCode.valueOf(200));
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundingRequest(@PathVariable Long id) {
        projectFundingRequestService.deleteFundingRequest(id);
        return ResponseEntity.noContent().build();
    }

}