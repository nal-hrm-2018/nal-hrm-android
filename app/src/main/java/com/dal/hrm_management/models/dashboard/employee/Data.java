package com.dal.hrm_management.models.dashboard.employee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("totalEmployee")
    @Expose
    private Integer totalEmployee;
    @SerializedName("official")
    @Expose
    private Integer official;
    @SerializedName("probation")
    @Expose
    private Integer probation;
    @SerializedName("internship")
    @Expose
    private Integer internship;
    @SerializedName("partTime")
    @Expose
    private Integer partTime;

    public Integer getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(Integer totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public Integer getOfficial() {
        return official;
    }

    public void setOfficial(Integer official) {
        this.official = official;
    }

    public Integer getProbation() {
        return probation;
    }

    public void setProbation(Integer probation) {
        this.probation = probation;
    }

    public Integer getInternship() {
        return internship;
    }

    public void setInternship(Integer internship) {
        this.internship = internship;
    }

    public Integer getPartTime() {
        return partTime;
    }

    public void setPartTime(Integer partTime) {
        this.partTime = partTime;
    }

}