package com.dal.hrm_management.models.absence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 24-Aug-18.
 */

public class AbsenceTime {

    @SerializedName("idAbsenceTime")
    @Expose
    private Integer idAbsenceTime;
    @SerializedName("nameAbsenceTime")
    @Expose
    private String nameAbsenceTime;
    @SerializedName("description")
    @Expose
    private String description;

    /**
     * No args constructor for use in serialization
     *
     */
    public AbsenceTime() {
    }

    /**
     *
     * @param idAbsenceTime
     * @param description
     * @param nameAbsenceTime
     */
    public AbsenceTime(Integer idAbsenceTime, String nameAbsenceTime, String description) {
        super();
        this.idAbsenceTime = idAbsenceTime;
        this.nameAbsenceTime = nameAbsenceTime;
        this.description = description;
    }

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
