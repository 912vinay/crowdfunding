package com.crowdfunding.api.repository;

import com.crowdfunding.entities.ProjectFundingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProjectFundingRequestRepository extends JpaRepository<ProjectFundingRequest, Long> {

}
