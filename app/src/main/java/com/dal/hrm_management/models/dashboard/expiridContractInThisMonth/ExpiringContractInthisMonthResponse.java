package com.dal.hrm_management.models.dashboard.expiridContractInThisMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExpiringContractInthisMonthResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataExpiringContract data;

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

    public DataExpiringContract getData() {
        return data;
    }

    public void setData(DataExpiringContract data) {
        this.data = data;
    }
}
