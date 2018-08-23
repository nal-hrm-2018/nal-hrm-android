package com.dal.hrm_management.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 23-Aug-18.
 */

public class RolesResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private List<Role> roles = null;

    /**
     * No args constructor for use in serialization
     */
    public RolesResponse() {
    }

    /**
     * @param roles
     * @param resultCode
     * @param resultMessage
     */
    public RolesResponse(Integer resultCode, String resultMessage, List<Role> roles) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.roles = roles;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}