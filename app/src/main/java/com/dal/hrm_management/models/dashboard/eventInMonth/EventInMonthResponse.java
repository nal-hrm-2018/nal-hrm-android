package com.dal.hrm_management.models.dashboard.eventInMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventInMonthResponse {
    @SerializedName("result_code")
    @Expose
    private Long resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataEvent data;

    public Long getResultCode() {
        return resultCode;
    }

    public void setResultCode(Long resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public DataEvent getData() {
        return data;
    }

    public void setData(DataEvent data) {
        this.data = data;
    }


}
