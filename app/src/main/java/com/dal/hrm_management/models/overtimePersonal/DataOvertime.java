package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataOvertime {
    @SerializedName("normal")
    @Expose
    private Double normal;
    @SerializedName("dayOff")
    @Expose
    private Double dayOff;
    @SerializedName("holiday")
    @Expose
    private Double holiday;
    @SerializedName("listDTO")
    @Expose
    private ListDTO listDTO;

    public Double getNormal() {
        return normal;
    }

    public void setNormal(Double normal) {
        this.normal = normal;
    }

    public Double getDayOff() {
        return dayOff;
    }

    public void setDayOff(Double dayOff) {
        this.dayOff = dayOff;
    }

    public Double getHoliday() {
        return holiday;
    }

    public void setHoliday(Double holiday) {
        this.holiday = holiday;
    }

    public ListDTO getListDTO() {
        return listDTO;
    }

    public void setListDTO(ListDTO listDTO) {
        this.listDTO = listDTO;
    }

}
