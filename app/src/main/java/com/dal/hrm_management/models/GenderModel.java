package com.dal.hrm_management.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenderModel {
    @SerializedName("gender_id")
    @Expose
    private Integer genderId;
    @SerializedName("gender_name")
    @Expose
    private String genderName;

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

}
