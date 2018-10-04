package com.dal.hrm_management.models.projectCompany;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectCompanyResponse {
    @SerializedName("result_code")
    @Expose
    private Long resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private List<Project> data = null;

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

    public List<Project> getData() {
        return data;
    }

    public void setData(List<Project> data) {
        this.data = data;
    }

}
