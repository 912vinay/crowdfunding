package com.crowdfunding.api.repository;

import com.crowdfunding.entities.ProjectFundingContributor;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProjectFundingContributorsRepository extends JpaRepository<ProjectFundingContributor, Long> {
}
