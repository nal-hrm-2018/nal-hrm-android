package com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public class ProjectInProgressPOResponse {
    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataProject data;

    /**
     * No args constructor for use in serialization
     *
     */
    public ProjectInProgressPOResponse() {
    }

    /**
     *
     * @param data
     * @param resultCode
     * @param resultMessage
     */
    public ProjectInProgressPOResponse(Integer resultCode, String resultMessage, DataProject data) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

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

    public DataProject getData() {
        return data;
    }

    public void setData(DataProject data) {
        this.data = data;
    }
}
