package com.vilgaxdevtask.test.services;

import com.vilgaxdevtask.test.domain.Backlog;
import com.vilgaxdevtask.test.domain.Project;
import com.vilgaxdevtask.test.domain.User;
import com.vilgaxdevtask.test.exceptions.ProjectIdException;
import com.vilgaxdevtask.test.exceptions.ProjectNotFoundException;
import com.vilgaxdevtask.test.repositories.BacklogRepository;
import com.vilgaxdevtask.test.repositories.ProjectRepository;
import com.vilgaxdevtask.test.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username) {

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            if (existingProject != null && (!existingProject.getProjectOwner().equals(username))) {
                throw new ProjectNotFoundException("Project not found in your account.");
            } else if (existingProject == null) {
                throw new ProjectNotFoundException("Project with ID '" + project.getProjectIdentifier() + "' cannot be updated because it doesn't exist.");
            }
        }

        try {
            User user = userRepository.findByUsername(username);

            project.setUser(user);
            project.setProjectOwner(user.getUsername());
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project with ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username) {

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project with ID '" + projectId + "' does not exists");
        }

        //Check for the user who is looking for the project is the project owner
        if (!project.getProjectOwner().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account.");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(String username) {
        return projectRepository.findAllByProjectOwner(username);
    }

    public void deleteProjectByIdentifier(String projectId, String username) {

        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
