package com.dal.hrm_management.models.listProjectEmpAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {

    @SerializedName("idRole")
    @Expose
    private Integer idRole;
    @SerializedName("nameRole")
    @Expose
    private String nameRole;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

}