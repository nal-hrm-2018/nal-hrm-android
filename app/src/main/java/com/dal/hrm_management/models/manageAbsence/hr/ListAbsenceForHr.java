package com.dal.hrm_management.models.manageAbsence.hr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ListAbsenceForHr implements Serializable {

    @SerializedName("idAbsences")
    @Expose
    private Integer idAbsences;
    @SerializedName("idEmployee")
    @Expose
    private Integer idEmployee;
    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;
    @SerializedName("idProject")
    @Expose
    private Object idProject;
    @SerializedName("nameProject")
    @Expose
    private Object nameProject;
    @SerializedName("fromDate")
    @Expose
    private String fromDate;
    @SerializedName("toDate")
    @Expose
    private String toDate;
    @SerializedName("reason")
    @Expose
    private String reason;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("absenceType")
    @Expose
    private AbsenceType absenceType;
    @SerializedName("absenceTime")
    @Expose
    private AbsenceTime absenceTime;
    @SerializedName("numberDayAbsence")
    @Expose
    private Double numberDayAbsence;

    public Integer getIdAbsences() {
        return idAbsences;
    }

    public void setIdAbsences(Integer idAbsences) {
        this.idAbsences = idAbsences;
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

    public Object getIdProject() {
        return idProject;
    }

    public void setIdProject(Object idProject) {
        this.idProject = idProject;
    }

    public Object getNameProject() {
        return nameProject;
    }

    public void setNameProject(Object nameProject) {
        this.nameProject = nameProject;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AbsenceType getAbsenceType() {
        return absenceType;
    }

    public void setAbsenceType(AbsenceType absenceType) {
        this.absenceType = absenceType;
    }

    public AbsenceTime getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(AbsenceTime absenceTime) {
        this.absenceTime = absenceTime;
    }

    public Double getNumberDayAbsence() {
        return numberDayAbsence;
    }

    public ListAbsenceForHr setNumberDayAbsence(Double numberDayAbsence) {
        this.numberDayAbsence = numberDayAbsence;
        return this;
    }
}