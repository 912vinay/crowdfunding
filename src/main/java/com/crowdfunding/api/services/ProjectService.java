package com.crowdfunding.api.services;

import com.crowdfunding.api.repository.ProjectRepository;
import com.crowdfunding.entities.Project;
import com.crowdfunding.exception.ProjectNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ProjectService {
    public static final String PROJECT_NOT_FOUND = "Project not Found";
    private final ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(long id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new ProjectNotFoundException(PROJECT_NOT_FOUND));
    }

    public List<Project> getProjectByEmail(String email) {
        return projectRepository.findByInnovatorEmail(email);

    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project update(Long id, Project project) {

        int updatedRows = projectRepository.updateProject(
                project.getStatus(),
                project.getDescription(),
                project.getProjectFundingRequests(),
                id);
        if (updatedRows ==  0) {
            throw new ProjectNotFoundException(PROJECT_NOT_FOUND+" with ID: " + id);
        }
        return project;
    }
}
