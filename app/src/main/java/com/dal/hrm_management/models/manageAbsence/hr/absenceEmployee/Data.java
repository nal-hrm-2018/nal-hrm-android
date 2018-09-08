package com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 30-Aug-18.
 */

public class Data {
    @SerializedName("allowAbsence")
    @Expose
    private Integer allowAbsence;
    @SerializedName("remainingAbsenceDays")
    @Expose
    private Integer remainingAbsenceDays;
    @SerializedName("annualLeave")
    @Expose
    private Integer annualLeave;
    @SerializedName("unpaidLeave")
    @Expose
    private Integer unpaidLeave;
    @SerializedName("marriageLeave")
    @Expose
    private Integer marriageLeave;
    @SerializedName("bereavementLeave")
    @Expose
    private Integer bereavementLeave;
    @SerializedName("maternityLeave")
    @Expose
    private Integer maternityLeave;
    @SerializedName("sickLeave")
    @Expose
    private Integer sickLeave;
    @SerializedName("listAbsence")
    @Expose
    private ListAbsence listAbsence;

    public Integer getAllowAbsence() {
        return allowAbsence;
    }

    public void setAllowAbsence(Integer allowAbsence) {
        this.allowAbsence = allowAbsence;
    }

    public Integer getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(Integer remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }

    public Integer getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Integer annualLeave) {
        this.annualLeave = annualLeave;
    }

    public Integer getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(Integer unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public Integer getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(Integer marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public Integer getBereavementLeave() {
        return bereavementLeave;
    }

    public void setBereavementLeave(Integer bereavementLeave) {
        this.bereavementLeave = bereavementLeave;
    }

    public Integer getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(Integer maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public Integer getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(Integer sickLeave) {
        this.sickLeave = sickLeave;
    }

    public ListAbsence getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(ListAbsence listAbsence) {
        this.listAbsence = listAbsence;
    }
}
