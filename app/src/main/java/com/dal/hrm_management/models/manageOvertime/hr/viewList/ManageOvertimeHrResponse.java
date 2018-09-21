package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ManageOvertimeHrResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataOvertime data;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public DataOvertime getData() {
        return data;
    }

    public void setData(DataOvertime data) {
        this.data = data;
    }

}