package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DayTypes implements Serializable {

    @SerializedName("idDayType")
    @Expose
    private Long idDayType;
    @SerializedName("nameDayType")
    @Expose
    private String nameDayType;

    public Long getIdDayType() {
        return idDayType;
    }

    public void setIdDayType(Long idDayType) {
        this.idDayType = idDayType;
    }

    public String getNameDayType() {
        return nameDayType;
    }

    public void setNameDayType(String nameDayType) {
        this.nameDayType = nameDayType;
    }

}