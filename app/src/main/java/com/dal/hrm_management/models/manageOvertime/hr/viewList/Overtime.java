package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */
public class Overtime {

    @SerializedName("id")
    @Expose
    private Long id;
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
    private Double totalTime;
    @SerializedName("correctTotalTime")
    @Expose
    private Double correctTotalTime;
    @SerializedName("reasonReject")
    @Expose
    private String reasonReject;
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
    @SerializedName("employeeId")
    @Expose
    private Long employeeId;
    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;
    @SerializedName("idProject")
    @Expose
    private String idProject;
    @SerializedName("nameProject")
    @Expose
    private String nameProject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public Double getCorrectTotalTime() {
        return correctTotalTime;
    }

    public void setCorrectTotalTime(Double correctTotalTime) {
        this.correctTotalTime = correctTotalTime;
    }

    public String getReasonReject() {
        return reasonReject;
    }

    public void setReasonReject(String reasonReject) {
        this.reasonReject = reasonReject;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
