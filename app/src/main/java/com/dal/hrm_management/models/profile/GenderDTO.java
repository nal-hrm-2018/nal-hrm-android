package com.dal.hrm_management.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenderDTO {

    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("nameGender")
    @Expose
    private String nameGender;

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNameGender() {
        return nameGender;
    }

    public void setNameGender(String nameGender) {
        this.nameGender = nameGender;
    }

}