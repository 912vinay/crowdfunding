package com.crowdfunding.api.repository;

import com.crowdfunding.entities.Project;
import com.crowdfunding.entities.ProjectFundingRequest;
import com.crowdfunding.entities.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project>  findByInnovatorEmail(String email);

    @Modifying
    @Transactional
    @Query("update Project p set p.status = ?1, p.description = ?2, p.projectFundingRequests = ?3 where p.id = ?4")
    int updateProject(Status status, String description, Set<ProjectFundingRequest> projectFundingRequests, Long id);

    @Transactional
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.projectFundingRequests WHERE p.id = :projectId")
    Optional<Project> findByIdWithFundingRequests(Long projectId);
    @Transactional
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.projectFundingRequests WHERE p.innovatorEmail = :innovatorEmail")
    List<Project> findByInnovatorEmailWithFundingRequests(String innovatorEmail);
    @Transactional
    @Query("SELECT DISTINCT p FROM Project p LEFT JOIN FETCH p.projectFundingRequests")
    List<Project> findAllWithFundingRequests();
}
