package com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("idStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("nameStatus")
    @Expose
    private String nameStatus;

    /**
     * No args constructor for use in serialization
     *
     */
    public Status() {
    }

    /**
     *
     * @param idStatus
     * @param nameStatus
     */
    public Status(Integer idStatus, String nameStatus) {
        super();
        this.idStatus = idStatus;
        this.nameStatus = nameStatus;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

}
