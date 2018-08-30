package com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 30-Aug-18.
 */

public class Absence {
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
    @SerializedName("absenceType")
    @Expose
    private AbsenceType absenceType;
    @SerializedName("absenceTime")
    @Expose
    private AbsenceTime absenceTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public Absence() {
    }

    /**
     *
     * @param employeeId
     * @param fromDate
     * @param reason
     * @param absenceTime
     * @param description
     * @param absenceType
     * @param absenceTimeId
     * @param toDate
     * @param absenceTypeId
     * @param idAbsences
     */
    public Absence(Integer idAbsences, Integer employeeId, Integer absenceTypeId, String fromDate, String toDate, String reason, String description, Integer absenceTimeId, AbsenceType absenceType, AbsenceTime absenceTime) {
        super();
        this.idAbsences = idAbsences;
        this.employeeId = employeeId;
        this.absenceTypeId = absenceTypeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.description = description;
        this.absenceTimeId = absenceTimeId;
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
