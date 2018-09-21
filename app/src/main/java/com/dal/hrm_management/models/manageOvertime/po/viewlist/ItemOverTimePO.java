package com.dal.hrm_management.models.manageOvertime.po.viewlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemOverTimePO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("totalTime")
    @Expose
    private Integer totalTime;
    @SerializedName("correctTotalTime")
    @Expose
    private Object correctTotalTime;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("dayTypes")
    @Expose
    private DayTypes dayTypes;
    @SerializedName("overtimeStatuses")
    @Expose
    private OvertimeStatuses overtimeStatuses;
    @SerializedName("idEmployee")
    @Expose
    private Integer idEmployee;
    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;
    @SerializedName("idProject")
    @Expose
    private String idProject;
    @SerializedName("nameProject")
    @Expose
    private String nameProject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Object getCorrectTotalTime() {
        return correctTotalTime;
    }

    public void setCorrectTotalTime(Object correctTotalTime) {
        this.correctTotalTime = correctTotalTime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public DayTypes getDayTypes() {
        return dayTypes;
    }

    public void setDayTypes(DayTypes dayTypes) {
        this.dayTypes = dayTypes;
    }

    public OvertimeStatuses getOvertimeStatuses() {
        return overtimeStatuses;
    }

    public void setOvertimeStatuses(OvertimeStatuses overtimeStatuses) {
        this.overtimeStatuses = overtimeStatuses;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }
}
