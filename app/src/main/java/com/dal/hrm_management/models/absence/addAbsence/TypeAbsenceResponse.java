package com.dal.hrm_management.models.absence.addAbsence;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeAbsenceResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private List<TypeAbsence> data = null;

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

    public List<TypeAbsence> getData() {
        return data;
    }

    public void setData(List<TypeAbsence> data) {
        this.data = data;
    }

}