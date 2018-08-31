package com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Project {
    @SerializedName("totalMember")
    @Expose
    private Integer totalMember;
    @SerializedName("idProject")
    @Expose
    private String idProject;
    @SerializedName("nameProject")
    @Expose
    private String nameProject;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("status")
    @Expose
    private Status status;

    /**
     * No args constructor for use in serialization
     *
     */
    public Project() {
    }

    /**
     *
     * @param startDate
     * @param status
     * @param totalMember
     * @param endDate
     * @param idProject
     * @param nameProject
     */
    public Project(Integer totalMember, String idProject, String nameProject, String startDate, Object endDate, Status status) {
        super();
        this.totalMember = totalMember;
        this.idProject = idProject;
        this.nameProject = nameProject;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}