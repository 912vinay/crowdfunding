package com.crowdfunding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectFundingRequestDto {
    private Long projectId;
    private Double amount;
    @NotNull(message = "Payment Details can't be null")
    private String paymentDetails;
}