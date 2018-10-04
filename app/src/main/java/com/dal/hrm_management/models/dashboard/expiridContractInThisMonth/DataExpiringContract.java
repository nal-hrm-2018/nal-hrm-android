package com.dal.hrm_management.models.dashboard.expiridContractInThisMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataExpiringContract {
    @SerializedName("internship")
    @Expose
    private Integer internship;
    @SerializedName("probation")
    @Expose
    private Integer probation;
    @SerializedName("oneYear")
    @Expose
    private Integer oneYear;
    @SerializedName("threeYear")
    @Expose
    private Integer threeYear;

    public Integer getInternship() {
        return internship;
    }

    public void setInternship(Integer internship) {
        this.internship = internship;
    }

    public Integer getProbation() {
        return probation;
    }

    public void setProbation(Integer probation) {
        this.probation = probation;
    }

    public Integer getOneYear() {
        return oneYear;
    }

    public void setOneYear(Integer oneYear) {
        this.oneYear = oneYear;
    }

    public Integer getThreeYear() {
        return threeYear;
    }

    public void setThreeYear(Integer threeYear) {
        this.threeYear = threeYear;
    }
}
