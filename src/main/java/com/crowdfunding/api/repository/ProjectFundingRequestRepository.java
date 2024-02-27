package com.crowdfunding.api.repository;

import com.crowdfunding.entities.ProjectFundingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ProjectFundingRequestRepository extends JpaRepository<ProjectFundingRequest, Long> {

    @Query("SELECT DISTINCT p FROM ProjectFundingRequest p LEFT JOIN FETCH p.projectFundingContributors WHERE p.requestId = :requestId")
    Optional<ProjectFundingRequest> findByIdWithAllFundingContributors(Long requestId);

    @Query("SELECT DISTINCT p FROM ProjectFundingRequest p LEFT JOIN FETCH p.projectFundingContributors ")
    List<ProjectFundingRequest> getAllProjectFundingRequestWithContributions();
}
