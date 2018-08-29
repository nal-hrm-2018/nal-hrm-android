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
    @SerializedName("absenceType")
    @Expose
    private AbsenceType absenceType;
    @SerializedName("absenceTime")
    @Expose
    private AbsenceTime absenceTime;

    /**
     * No args constructor for use in serialization
     */
    public Absence() {
    }

    /**
     * @param employeeId
     * @param fromDate
     * @param reason
     * @param absenceTime
     * @param description
     * @param absenceType
     * @param toDate
     * @param idAbsences
     */
    public Absence(Integer idAbsences, Integer employeeId, String fromDate, String toDate, String reason, String description, AbsenceType absenceType, AbsenceTime absenceTime) {
        super();
        this.idAbsences = idAbsences;
        this.employeeId = employeeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.description = description;
        this.absenceType = absenceType;
        this.absenceTime = absenceTime;
    }

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