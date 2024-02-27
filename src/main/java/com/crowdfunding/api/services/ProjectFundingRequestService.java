package com.crowdfunding.api.services;

import com.crowdfunding.api.repository.ProjectFundingRequestRepository;
import com.crowdfunding.api.repository.ProjectRepository;
import com.crowdfunding.entities.ProjectFundingRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProjectFundingRequestService {

    private final ProjectFundingRequestRepository projectFundingRequestRepository;
    private final ProjectRepository projectRepository;

    public  void deleteFundingRequest(Long id) {
        projectFundingRequestRepository.deleteById(id);
    }


    public List<ProjectFundingRequest> getAllProjectFundingRequest()
    {
        return projectFundingRequestRepository.findAll();
    }


    public ProjectFundingRequest fundingRequest(ProjectFundingRequest projectFundingRequest)
    {
        return projectFundingRequestRepository.save(projectFundingRequest);
    }




    // CRUD operations methods here
}
