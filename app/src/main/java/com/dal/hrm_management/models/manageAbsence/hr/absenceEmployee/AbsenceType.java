package com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 30-Aug-18.
 */

public class AbsenceType {
    @SerializedName("idAbsenceType")
    @Expose
    private Integer idAbsenceType;
    @SerializedName("nameAbsenceType")
    @Expose
    private String nameAbsenceType;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public AbsenceType() {
    }

    /**
     *
     * @param description
     * @param idAbsenceType
     * @param nameAbsenceType
     */
    public AbsenceType(Integer idAbsenceType, String nameAbsenceType, String description) {
        super();
        this.idAbsenceType = idAbsenceType;
        this.nameAbsenceType = nameAbsenceType;
        this.description = description;
    }

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

}
