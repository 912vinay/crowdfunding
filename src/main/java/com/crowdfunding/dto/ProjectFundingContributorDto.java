package com.crowdfunding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectFundingContributorDto {
    private Long requestId;
    @NotNull(message = "Email can not be null")
    private String contributorEmail;
    private Double contributionAmount;
}