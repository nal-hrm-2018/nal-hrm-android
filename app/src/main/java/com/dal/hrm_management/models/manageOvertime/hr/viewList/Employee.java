package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

public class Employee {

    @SerializedName("idEmployee")
    @Expose
    private Long idEmployee;
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
    private Object company;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
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

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
