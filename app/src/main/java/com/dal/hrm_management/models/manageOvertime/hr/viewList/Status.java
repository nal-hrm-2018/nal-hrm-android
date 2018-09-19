package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

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