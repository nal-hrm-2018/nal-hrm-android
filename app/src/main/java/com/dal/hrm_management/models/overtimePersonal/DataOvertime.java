package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataOvertime {
    @SerializedName("normal")
    @Expose
    private Long normal;
    @SerializedName("dayOff")
    @Expose
    private Long dayOff;
    @SerializedName("holiday")
    @Expose
    private Long holiday;
    @SerializedName("listDTO")
    @Expose
    private ListDTO listDTO;

    public Long getNormal() {
        return normal;
    }

    public void setNormal(Long normal) {
        this.normal = normal;
    }

    public Long getDayOff() {
        return dayOff;
    }

    public void setDayOff(Long dayOff) {
        this.dayOff = dayOff;
    }

    public Long getHoliday() {
        return holiday;
    }

    public void setHoliday(Long holiday) {
        this.holiday = holiday;
    }

    public ListDTO getListDTO() {
        return listDTO;
    }

    public void setListDTO(ListDTO listDTO) {
        this.listDTO = listDTO;
    }

}