package com.crowdfunding.api.services;

import com.crowdfunding.api.repository.ProjectFundingRequestRepository;
import com.crowdfunding.api.repository.ProjectRepository;
import com.crowdfunding.dto.ProjectFundingRequestDto;
import com.crowdfunding.entities.Project;
import com.crowdfunding.entities.ProjectFundingRequest;
import com.crowdfunding.exception.ProjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProjectFundingRequestService {

    private final ProjectFundingRequestRepository projectFundingRequestRepository;
    private final ProjectRepository projectRepository;

    public void deleteFundingRequest(Long id) {
        projectFundingRequestRepository.deleteById(id);
    }


    public List<ProjectFundingRequest> getAllProjectFundingRequest() {
        return projectFundingRequestRepository.getAllProjectFundingRequestWithContributions();
    }


    public ProjectFundingRequest fundingRequest(ProjectFundingRequestDto projectFundingRequestDto) {
        Project project = projectRepository.
                findById(projectFundingRequestDto.getProjectId()).
                orElseThrow(() -> new ProjectNotFoundException("Project not found with id " + projectFundingRequestDto.getProjectId()));

        ProjectFundingRequest projectFundingRequest=new ProjectFundingRequest();
        projectFundingRequest.setProject(project);
        projectFundingRequest.setAmount(projectFundingRequestDto.getAmount());
        projectFundingRequest.setPaymentDetails(projectFundingRequestDto.getPaymentDetails());

        return projectFundingRequestRepository.save(projectFundingRequest);
    }

    public Optional<ProjectFundingRequest> findById(Long requestId) {
        return projectFundingRequestRepository.findByIdWithAllFundingContributors(requestId);
    }


    // CRUD operations methods here
}
