package com.dal.hrm_management.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 23-Aug-18.
 */
public class TeamsResponse {

    @SerializedName("result_code")
    @Expose
    private Integer resultCode;
    @SerializedName("result_message")
    @Expose
    private String resultMessage;
    @SerializedName("data")
    @Expose
    private List<Team> teams = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeamsResponse() {
    }

    /**
     *
     * @param teams
     * @param resultCode
     * @param resultMessage
     */
    public TeamsResponse(Integer resultCode, String resultMessage, List<Team> teams) {
        super();
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.teams = teams;
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

}
