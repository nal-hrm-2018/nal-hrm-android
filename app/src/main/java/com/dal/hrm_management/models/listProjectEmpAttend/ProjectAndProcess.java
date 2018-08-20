package com.dal.hrm_management.models.listProjectEmpAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectAndProcess {

    @SerializedName("project")
    @Expose
    private Project project;
    @SerializedName("processes")
    @Expose
    private Processes processes;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Processes getProcesses() {
        return processes;
    }

    public void setProcesses(Processes processes) {
        this.processes = processes;
    }

}