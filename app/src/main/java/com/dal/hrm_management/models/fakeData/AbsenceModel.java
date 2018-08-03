package com.dal.hrm_management.models.fakeData;

public class AbsenceModel {
    private String type;
    private String dateStart;
    private String dateEnd;
    private String reason;
    private String status;

    public AbsenceModel(String type, String dateStart, String dateEnd, String reason, String status) {
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.reason = reason;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
