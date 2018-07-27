package com.dal.hrm_management.models;

/**
 * Created by Luu Ngoc Lan on 27-Jul-18.
 */

public class ProjectEmployee {
    private String nameProject;
    private String role;
    private String startDay;
    private String endDay;
    private String status;

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProjectEmployee(String nameProject, String role, String startDay, String endDay, String status) {
        this.nameProject = nameProject;
        this.role = role;
        this.startDay = startDay;
        this.endDay = endDay;
        this.status = status;
    }
}
