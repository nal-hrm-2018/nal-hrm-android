package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */
public class Overtime {

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
    private Double totalTime;
    @SerializedName("correctTotalTime")
    @Expose
    private Double correctTotalTime;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("overtimeTypes")
    @Expose
    private OvertimeTypes overtimeTypes;
    @SerializedName("overtimeStatuses")
    @Expose
    private OvertimeStatuses overtimeStatuses;
    @SerializedName("employee")
    @Expose
    private Employee employee;
    @SerializedName("project")
    @Expose
    private Project project;

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

    public OvertimeTypes getOvertimeTypes() {
        return overtimeTypes;
    }

    public void setOvertimeTypes(OvertimeTypes overtimeTypes) {
        this.overtimeTypes = overtimeTypes;
    }

    public OvertimeStatuses getOvertimeStatuses() {
        return overtimeStatuses;
    }

    public void setOvertimeStatuses(OvertimeStatuses overtimeStatuses) {
        this.overtimeStatuses = overtimeStatuses;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
