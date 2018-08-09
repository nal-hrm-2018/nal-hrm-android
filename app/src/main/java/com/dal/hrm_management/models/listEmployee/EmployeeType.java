package com.dal.hrm_management.models.listEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeType {

    @SerializedName("idEmployeeType")
    @Expose
    private Integer idEmployeeType;
    @SerializedName("nameEmployeeType")
    @Expose
    private String nameEmployeeType;

    public Integer getIdEmployeeType() {
        return idEmployeeType;
    }

    public void setIdEmployeeType(Integer idEmployeeType) {
        this.idEmployeeType = idEmployeeType;
    }

    public String getNameEmployeeType() {
        return nameEmployeeType;
    }

    public void setNameEmployeeType(String nameEmployeeType) {
        this.nameEmployeeType = nameEmployeeType;
    }

}