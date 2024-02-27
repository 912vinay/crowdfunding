package com.crowdfunding.api.controller;

import com.crowdfunding.api.services.ProjectFundingContributorsService;
import com.crowdfunding.dto.ProjectFundingContributorDto;
import com.crowdfunding.dto.ProjectFundingRequestDto;
import com.crowdfunding.entities.ProjectFundingContributor;
import com.crowdfunding.entities.ProjectFundingRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/projectFundingContributor")
@AllArgsConstructor
@Validated
@Slf4j
public class ProjectFundingContributorController {

    private final ProjectFundingContributorsService projectFundingContributorsService;

    @PostMapping("/contribute")
    public ResponseEntity<ProjectFundingContributor> contributeFund(@RequestBody ProjectFundingContributorDto projectFundingContributorDto) {

            ProjectFundingContributor projectFundingRequest = projectFundingContributorsService.contributeFund(projectFundingContributorDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(projectFundingRequest);
    }

}