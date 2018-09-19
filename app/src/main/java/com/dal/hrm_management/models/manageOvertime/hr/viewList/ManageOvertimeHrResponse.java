package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

public class ManageOvertimeHrResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataOvertime dataOvertime;

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

    public DataOvertime getDataOvertime() {
        return dataOvertime;
    }

    public void setDataOvertime(DataOvertime dataOvertime) {
        this.dataOvertime = dataOvertime;
    }

}