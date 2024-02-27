package com.crowdfunding.api.services;

import com.crowdfunding.api.repository.ProjectFundingContributorsRepository;
import com.crowdfunding.dto.ProjectFundingContributorDto;
import com.crowdfunding.entities.ProjectFundingContributor;
import com.crowdfunding.entities.ProjectFundingRequest;
import com.crowdfunding.entities.Status;
import com.crowdfunding.exception.FundingNotRequiredException;
import com.crowdfunding.exception.FundingProcessingException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class ProjectFundingContributorsService {

    private final ProjectFundingContributorsRepository projectFundingContributorsRepository;
    private final ProjectFundingRequestService projectFundingRequestService;


    public ProjectFundingContributor contributeFund(ProjectFundingContributorDto projectFundingContributorDto) {
        ProjectFundingRequest projectFundingRequest = projectFundingRequestService.
                findById(projectFundingContributorDto.getRequestId())
                .orElseThrow(() -> new FundingProcessingException("No Funding Request present requestId " + projectFundingContributorDto.getRequestId()));
        validate(projectFundingContributorDto, projectFundingRequest);
        synchronized (projectFundingRequest) {

            projectFundingRequest.setAmount(projectFundingRequest.getAmount() - projectFundingContributorDto.getContributionAmount());
            updateProjectStatus(projectFundingRequest);
            ProjectFundingContributor projectFundingContributor = new ProjectFundingContributor();
            projectFundingContributor.setContributorEmail(projectFundingContributorDto.getContributorEmail());
            projectFundingContributor.setProjectFundingRequest(projectFundingRequest);
            projectFundingContributor.setContributionAmount(projectFundingContributorDto.getContributionAmount());
            return projectFundingContributorsRepository.save(projectFundingContributor);
        }
    }

    private void validate(ProjectFundingContributorDto projectFundingContributorDto, ProjectFundingRequest projectFundingRequest) {
        if (projectFundingRequest.getProject().getStatus().equals(Status.ARCHIVED))
            throw new FundingNotRequiredException("Project already funded or archived");

        if (projectFundingContributorDto.getContributionAmount() <= 0 ||
                projectFundingContributorDto.getContributionAmount() > projectFundingRequest.getAmount()) {
            throw new FundingProcessingException("Invalid contribution amount");
        }
    }


    private void updateProjectStatus(ProjectFundingRequest projectFundingRequest) {
        boolean isArchived = projectFundingRequest.getProject().getProjectFundingRequests().stream()
                .allMatch(request -> request.getAmount() <= 0);

        if (isArchived) {
            projectFundingRequest.getProject().setStatus(Status.ARCHIVED);
        }
    }


}
