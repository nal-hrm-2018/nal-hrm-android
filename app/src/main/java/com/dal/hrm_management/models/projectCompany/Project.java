package com.dal.hrm_management.models.projectCompany;

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
    private String endDate;
    @SerializedName("namePO")
    @Expose
    private String namePO;
    @SerializedName("status")
    @Expose
    private Status status;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNamePO() {
        return namePO;
    }

    public void setNamePO(String namePO) {
        this.namePO = namePO;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
