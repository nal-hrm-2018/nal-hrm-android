package com.dal.hrm_management.models.absence.addAbsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbsenceForm {
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

    public AbsenceForm(Integer absenceTypeId, String fromDate, String toDate, String reason, String description, Integer absenceTimeId) {
        this.absenceTypeId = absenceTypeId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reason = reason;
        this.description = description;
        this.absenceTimeId = absenceTimeId;
    }
}
