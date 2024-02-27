package com.crowdfunding.api.services;

import com.crowdfunding.api.repository.ProjectFundingContributorsRepository;
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


    public void contributeFund(ProjectFundingContributor projectFundingContributor) {
        ProjectFundingRequest projectFundingRequest = projectFundingContributor.getProjectFundingRequest();
        validate(projectFundingContributor, projectFundingRequest);
        synchronized (projectFundingRequest) {
            projectFundingRequest.setAmount(projectFundingRequest.getAmount() - projectFundingContributor.getContributionAmount());
            updateProjectStatus(projectFundingRequest);
            projectFundingContributorsRepository.save(projectFundingContributor);
        }
    }

    private void validate(ProjectFundingContributor projectFundingContributor, ProjectFundingRequest projectFundingRequest) {
        if (projectFundingRequest.getAmount() == 0 || projectFundingRequest.getProject().getStatus().equals(Status.ARCHIVED))
            throw new FundingNotRequiredException("Project already funded or archived");

        if (projectFundingContributor.getContributionAmount() <= 0 ||
                projectFundingContributor.getContributionAmount() > projectFundingRequest.getAmount()) {
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
