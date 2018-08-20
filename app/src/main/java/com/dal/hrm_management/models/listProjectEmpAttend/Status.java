package com.dal.hrm_management.models.listProjectEmpAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("idStatus")
    @Expose
    private Integer idStatus;
    @SerializedName("nameStatus")
    @Expose
    private String nameStatus;

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