package com.dal.hrm_management.models.holiday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayTypes {

    @SerializedName("idDayType")
    @Expose
    private Integer idDayType;
    @SerializedName("nameDayType")
    @Expose
    private String nameDayType;

    public Integer getIdDayType() {
        return idDayType;
    }

    public void setIdDayType(Integer idDayType) {
        this.idDayType = idDayType;
    }

    public String getNameDayType() {
        return nameDayType;
    }

    public void setNameDayType(String nameDayType) {
        this.nameDayType = nameDayType;
    }


}