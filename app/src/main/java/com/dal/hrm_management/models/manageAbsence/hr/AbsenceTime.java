package com.dal.hrm_management.models.manageAbsence.hr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AbsenceTime implements Serializable{

    @SerializedName("idAbsenceTime")
    @Expose
    private Integer idAbsenceTime;
    @SerializedName("nameAbsenceTime")
    @Expose
    private String nameAbsenceTime;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getIdAbsenceTime() {
        return idAbsenceTime;
    }

    public void setIdAbsenceTime(Integer idAbsenceTime) {
        this.idAbsenceTime = idAbsenceTime;
    }

    public String getNameAbsenceTime() {
        return nameAbsenceTime;
    }

    public void setNameAbsenceTime(String nameAbsenceTime) {
        this.nameAbsenceTime = nameAbsenceTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}