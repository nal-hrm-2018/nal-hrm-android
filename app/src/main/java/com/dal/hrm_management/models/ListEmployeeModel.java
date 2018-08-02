package com.dal.hrm_management.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListEmployeeModel {

    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private List<ItemListEmployeeModel> data = null;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<ItemListEmployeeModel> getData() {
        return data;
    }

    public void setData(List<ItemListEmployeeModel> data) {
        this.data = data;
    }

}