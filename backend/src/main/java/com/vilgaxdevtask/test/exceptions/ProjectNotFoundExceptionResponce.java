package com.vilgaxdevtask.test.exceptions;

public class ProjectNotFoundExceptionResponce {

    private String projectNotFound;

    public ProjectNotFoundExceptionResponce(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
