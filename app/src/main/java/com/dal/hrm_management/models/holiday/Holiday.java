package com.dal.hrm_management.models.holiday;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Holiday {

    @SerializedName("idHoliday")
    @Expose
    private Integer idHoliday;
    @SerializedName("dateHoliday")
    @Expose
    private String dateHoliday;
    @SerializedName("nameHoliday")
    @Expose
    private String nameHoliday;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dayTypes")
    @Expose
    private DayTypes dayTypes;

    public Integer getIdHoliday() {
        return idHoliday;
    }

    public void setIdHoliday(Integer idHoliday) {
        this.idHoliday = idHoliday;
    }

    public String getDateHoliday() {
        return dateHoliday;
    }

    public void setDateHoliday(String dateHoliday) {
        this.dateHoliday = dateHoliday;
    }

    public String getNameHoliday() {
        return nameHoliday;
    }

    public void setNameHoliday(String nameHoliday) {
        this.nameHoliday = nameHoliday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DayTypes getDayTypes() {
        return dayTypes;
    }

    public void setDayTypes(DayTypes dayTypes) {
        this.dayTypes = dayTypes;
    }

}