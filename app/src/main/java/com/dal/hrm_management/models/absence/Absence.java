package com.dal.hrm_management.models.absence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Luu Ngoc Lan on 02-Aug-18.
 */
public class Absence implements Serializable {

    @SerializedName("idAbsences")
    @Expose
    private Integer idAbsences;
    @SerializedName("employeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("absenceTypeId")
    @Expose
    private Integer absenceTypeId;
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
    @SerializedName("absenceTimeId")
    @Expose
    private Integer absenceTimeId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("absenceType")
    @Expose
    private AbsenceType absenceType;
    @SerializedName("absenceTime")
    @Expose
    private AbsenceTime absenceTime;

    public Integer getIdAbsences() {
        return idAbsences;
    }

    public void setIdAbsences(Integer idAbsences) {
        this.idAbsences = idAbsences;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAbsenceTypeId() {
        return absenceTypeId;
    }

    public void setAbsenceTypeId(Integer absenceTypeId) {
        this.absenceTypeId = absenceTypeId;
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

    public Integer getAbsenceTimeId() {
        return absenceTimeId;
    }

    public void setAbsenceTimeId(Integer absenceTimeId) {
        this.absenceTimeId = absenceTimeId;
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

}