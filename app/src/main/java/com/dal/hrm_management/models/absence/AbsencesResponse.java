package com.dal.hrm_management.models.absence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 24-Aug-18.
 */

public class AbsencesResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private DataAbsence dataAbsence;

    /**
     * No args constructor for use in serialization
     */
    public AbsencesResponse() {
    }

    /**
     * @param resultCode
     * @param dataAbsence
     * @param resultMessage
     */
    public AbsencesResponse(Integer resultCode, String resultMessage, DataAbsence dataAbsence) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.dataAbsence = dataAbsence;
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

    public DataAbsence getDataAbsence() {
        return dataAbsence;
    }

    public void setDataAbsence(DataAbsence dataAbsence) {
        this.dataAbsence = dataAbsence;
    }

}