package com.dal.hrm_management.adapter.listOT;


public class FakeDataForListOvertime {
    private String NameProject;
    private String Date;
    private String FromTime;
    private String ToTime;
    private String TotalTime;
    private String Reason;
    private String Status;
    private String acceptTime;

    public FakeDataForListOvertime(String nameProject, String date, String fromTime, String toTime, String totalTime, String reason, String status, String acceptTime) {
        NameProject = nameProject;
        Date = date;
        FromTime = fromTime;
        ToTime = toTime;
        TotalTime = totalTime;
        Reason = reason;
        Status = status;
        this.acceptTime = acceptTime;
    }

    public String getNameProject() {
        return NameProject;
    }

    public FakeDataForListOvertime setNameProject(String nameProject) {
        NameProject = nameProject;
        return this;
    }

    public String getDate() {
        return Date;
    }

    public FakeDataForListOvertime setDate(String date) {
        Date = date;
        return this;
    }

    public String getFromTime() {
        return FromTime;
    }

    public FakeDataForListOvertime setFromTime(String fromTime) {
        FromTime = fromTime;
        return this;
    }

    public String getToTime() {
        return ToTime;
    }

    public FakeDataForListOvertime setToTime(String toTime) {
        ToTime = toTime;
        return this;
    }

    public String getTotalTime() {
        return TotalTime;
    }

    public FakeDataForListOvertime setTotalTime(String totalTime) {
        TotalTime = totalTime;
        return this;
    }

    public String getReason() {
        return Reason;
    }

    public FakeDataForListOvertime setReason(String reason) {
        Reason = reason;
        return this;
    }

    public String getStatus() {
        return Status;
    }

    public FakeDataForListOvertime setStatus(String status) {
        Status = status;
        return this;
    }

    public String getAcceptTime() {
        return acceptTime;
    }

    public FakeDataForListOvertime setAcceptTime(String acceptTime) {
        this.acceptTime = acceptTime;
        return this;
    }
}
