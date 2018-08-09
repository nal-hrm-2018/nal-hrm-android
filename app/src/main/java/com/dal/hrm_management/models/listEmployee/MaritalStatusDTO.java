package com.dal.hrm_management.models.listEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaritalStatusDTO {

    @SerializedName("maritalStatus")
    @Expose
    private Integer maritalStatus;
    @SerializedName("typeMaritalStatus")
    @Expose
    private String typeMaritalStatus;

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getTypeMaritalStatus() {
        return typeMaritalStatus;
    }

    public void setTypeMaritalStatus(String typeMaritalStatus) {
        this.typeMaritalStatus = typeMaritalStatus;
    }

}