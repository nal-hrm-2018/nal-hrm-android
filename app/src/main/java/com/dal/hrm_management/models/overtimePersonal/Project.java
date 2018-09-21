package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Project implements Serializable{
    @SerializedName("idProject")
    @Expose
    private String idProject;
    @SerializedName("nameProject")
    @Expose
    private String nameProject;
    @SerializedName("income")
    @Expose
    private Integer income;
    @SerializedName("realCost")
    @Expose
    private Integer realCost;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("estimateStartDate")
    @Expose
    private String estimateStartDate;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("estimateEndDate")
    @Expose
    private String estimateEndDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("status")
    @Expose
    private Status status;

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

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Integer getRealCost() {
        return realCost;
    }

    public void setRealCost(Integer realCost) {
        this.realCost = realCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEstimateStartDate() {
        return estimateStartDate;
    }

    public void setEstimateStartDate(String estimateStartDate) {
        this.estimateStartDate = estimateStartDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEstimateEndDate() {
        return estimateEndDate;
    }

    public void setEstimateEndDate(String estimateEndDate) {
        this.estimateEndDate = estimateEndDate;
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
