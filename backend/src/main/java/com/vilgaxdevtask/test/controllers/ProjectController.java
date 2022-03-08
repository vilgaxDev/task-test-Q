package com.vilgaxdevtask.test.controllers;

import com.vilgaxdevtask.test.domain.Project;
import com.vilgaxdevtask.test.services.MapValidationErrorService;
import com.vilgaxdevtask.test.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        Project project1 = projectService.saveOrUpdateProject(project, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{TaskId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal) {

        Project project = projectService.findProjectByIdentifier(projectId, principal.getName());

        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{TaskId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal) {
        projectService.deleteProjectByIdentifier(projectId.toUpperCase(), principal.getName());

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' was deleted", HttpStatus.OK);
    }

    @PutMapping("{TaskId}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, @PathVariable String projectId, Principal principal) {
        Project project1 = projectService.findProjectByIdentifier(projectId.toUpperCase(), principal.getName());

        project1.setProjectName(project.getProjectName());
        project1.setProjectIdentifier(project.getProjectIdentifier());
        project1.setDescription(project.getDescription());

        final Project updatedProject = projectService.saveOrUpdateProject(project1, principal.getName());

        return new ResponseEntity<Project>(updatedProject, HttpStatus.OK);
    }
}
