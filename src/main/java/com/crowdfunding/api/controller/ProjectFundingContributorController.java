package com.crowdfunding.api.controller;

import com.crowdfunding.api.services.ProjectFundingContributorsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectFundingContributorController {

    private final ProjectFundingContributorsService projectFundingContributorsService;

}