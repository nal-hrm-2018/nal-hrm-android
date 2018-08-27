package com.dal.hrm_management.models.fakeData;

/**
 * Created by Luu Ngoc Lan on 02-Aug-18.
 */
public class Absence {
    private String name;
    private String nameProject;
    private String from;
    private String to;
    private String status;
    private String note;
    private String reason;
    private String type;

    public Absence(String name, String nameProject, String from, String to, String status, String note, String reason, String type) {
        this.name = name;
        this.nameProject = nameProject;
        this.from = from;
        this.to = to;
        this.status = status;
        this.note = note;
        this.reason = reason;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
