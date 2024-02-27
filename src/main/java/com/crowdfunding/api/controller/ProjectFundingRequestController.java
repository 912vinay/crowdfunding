package com.crowdfunding.api.controller;

import com.crowdfunding.api.repository.ProjectRepository;
import com.crowdfunding.api.services.ProjectFundingRequestService;
import com.crowdfunding.api.services.ProjectService;
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
    public ResponseEntity<ProjectFundingRequest> fundingRequest( @RequestBody ProjectFundingRequest projectFundingRequest) {

        try {

            if (projectFundingRequest == null) {
                return ResponseEntity.badRequest().build();
            }
             projectFundingRequest = projectFundingRequestService.fundingRequest(projectFundingRequest);
             return ResponseEntity.status(HttpStatus.CREATED).body(projectFundingRequest);
        } catch (Exception e) {
            log.error("Failed to create project", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all projects
    @GetMapping
    public List<ProjectFundingRequest> getAllProjectFundingRequest() {
        return projectFundingRequestService.getAllProjectFundingRequest();
    }

    // Get a project by ID
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Set<ProjectFundingRequest>> getProjectById(@PathVariable Long projectId) {
        Set<ProjectFundingRequest> allFundingRequestByProject = projectService.getProjectById(projectId).getProjectFundingRequests();
        return new ResponseEntity<>(allFundingRequestByProject,HttpStatusCode.valueOf(200));
    }

    // Delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundingRequest(@PathVariable Long id) {
        projectFundingRequestService.deleteFundingRequest(id);
        return ResponseEntity.noContent().build();
    }

}