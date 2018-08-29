package com.dal.hrm_management.models.absence.addAbsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeAbsence {
    @SerializedName("idAbsenceType")
    @Expose
    private Integer idAbsenceType;
    @SerializedName("nameAbsenceType")
    @Expose
    private String nameAbsenceType;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getIdAbsenceType() {
        return idAbsenceType;
    }

    public void setIdAbsenceType(Integer idAbsenceType) {
        this.idAbsenceType = idAbsenceType;
    }

    public String getNameAbsenceType() {
        return nameAbsenceType;
    }

    public void setNameAbsenceType(String nameAbsenceType) {
        this.nameAbsenceType = nameAbsenceType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return nameAbsenceType.replace("_"," ");
    }
}
