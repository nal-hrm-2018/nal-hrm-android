package com.dal.hrm_management.models.projectCompany;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("idStatus")
    @Expose
    private Long idStatus;
    @SerializedName("nameStatus")
    @Expose
    private String nameStatus;

    public Long getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Long idStatus) {
        this.idStatus = idStatus;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }
}
