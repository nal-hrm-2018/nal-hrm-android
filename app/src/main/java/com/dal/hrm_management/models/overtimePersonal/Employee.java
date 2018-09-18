package com.dal.hrm_management.models.overtimePersonal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("idEmployee")
    @Expose
    private Integer idEmployee;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("startWorkDate")
    @Expose
    private String startWorkDate;
    @SerializedName("endWorkDate")
    @Expose
    private String endWorkDate;
    @SerializedName("curriculum_vitae")
    @Expose
    private Object curriculumVitae;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(String endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public Object getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(Object curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}