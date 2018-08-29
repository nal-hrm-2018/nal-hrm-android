package com.dal.hrm_management.models.manageAbsence.hr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListAbsenceForHr {

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
    private Object description;
    @SerializedName("absenceTimeId")
    @Expose
    private Integer absenceTimeId;
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

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Integer getAbsenceTimeId() {
        return absenceTimeId;
    }

    public void setAbsenceTimeId(Integer absenceTimeId) {
        this.absenceTimeId = absenceTimeId;
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