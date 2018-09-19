package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataOvertime {
    @SerializedName("normal")
    @Expose
    private Integer normal;
    @SerializedName("dayOff")
    @Expose
    private Integer dayOff;
    @SerializedName("holiday")
    @Expose
    private Integer holiday;
    @SerializedName("listDTO")
    @Expose
    private ListDTO listDTO;

    public Integer getNormal() {
        return normal;
    }

    public void setNormal(Integer normal) {
        this.normal = normal;
    }

    public Integer getDayOff() {
        return dayOff;
    }

    public void setDayOff(Integer dayOff) {
        this.dayOff = dayOff;
    }

    public Integer getHoliday() {
        return holiday;
    }

    public void setHoliday(Integer holiday) {
        this.holiday = holiday;
    }

    public ListDTO getListDTO() {
        return listDTO;
    }

    public void setListDTO(ListDTO listDTO) {
        this.listDTO = listDTO;
    }

}
