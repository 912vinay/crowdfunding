package com.crowdfunding.api.repository;

import com.crowdfunding.entities.Project;
import com.crowdfunding.entities.ProjectFundingRequest;
import com.crowdfunding.entities.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project>  findByInnovatorEmail(String email);

    @Modifying
    @Transactional
    @Query("update Project p set p.status = ?1, p.description = ?2, p.projectFundingRequests = ?3 where p.id = ?4")
    int updateProject(Status status, String description, Set<ProjectFundingRequest> projectFundingRequests, Long id);
}
