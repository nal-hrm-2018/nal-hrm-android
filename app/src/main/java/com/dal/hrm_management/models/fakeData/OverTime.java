package com.dal.hrm_management.models.fakeData;

/**
 * Created by Luu Ngoc Lan on 06-Sep-18.
 */

public class OverTime {
    private String nameEmployee;
    private String nameProject;
    private String date;
    private String from;
    private String to;
    private String numberTime;
    private String acceptTime;
    private String status;
    private String reason;
    private String typeDate;

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNumberTime() {
        return numberTime;
    }

    public void setNumberTime(String numberTime) {
        this.numberTime = numberTime;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTypeDate() {
        return typeDate;
    }

    public void setTypeDate(String typeDate) {
        this.typeDate = typeDate;
    }

    public OverTime(String nameEmployee, String nameProject, String date, String from, String to, String numberTime, String acceptTime, String status, String reason, String typeDate) {
        this.nameEmployee = nameEmployee;
        this.nameProject = nameProject;
        this.date = date;
        this.from = from;
        this.to = to;
        this.numberTime = numberTime;
        this.acceptTime = acceptTime;
        this.status = status;
        this.reason = reason;
        this.typeDate = typeDate;
    }
}
