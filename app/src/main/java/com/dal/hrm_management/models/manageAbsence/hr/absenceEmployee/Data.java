package com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 30-Aug-18.
 */

public class Data {
    @SerializedName("allowAbsence")
    @Expose
    private Double allowAbsence;
    @SerializedName("remainingAbsenceDays")
    @Expose
    private Double remainingAbsenceDays;
    @SerializedName("totalRemain")
    @Expose
    private Double totalRemain;
    @SerializedName("annualLeave")
    @Expose
    private Double annualLeave;
    @SerializedName("unpaidLeave")
    @Expose
    private Double unpaidLeave;
    @SerializedName("marriageLeave")
    @Expose
    private Double marriageLeave;
    @SerializedName("bereavementLeave")
    @Expose
    private Double bereavementLeave;
    @SerializedName("maternityLeave")
    @Expose
    private Double maternityLeave;
    @SerializedName("sickLeave")
    @Expose
    private Double sickLeave;
    @SerializedName("listAbsence")
    @Expose
    private ListAbsence listAbsence;

    public Double getAllowAbsence() {
        return allowAbsence;
    }

    public void setAllowAbsence(Double allowAbsence) {
        this.allowAbsence = allowAbsence;
    }

    public Double getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(Double remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }
    public Double getTotalRemain() {
        return totalRemain;
    }

    public void setTotalRemain(Double totalRemain) {
        this.totalRemain = totalRemain;
    }
    public Double getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(Double annualLeave) {
        this.annualLeave = annualLeave;
    }

    public Double getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(Double unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public Double getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(Double marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public Double getBereavementLeave() {
        return bereavementLeave;
    }

    public void setBereavementLeave(Double bereavementLeave) {
        this.bereavementLeave = bereavementLeave;
    }

    public Double getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(Double maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public Double getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(Double sickLeave) {
        this.sickLeave = sickLeave;
    }

    public ListAbsence getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(ListAbsence listAbsence) {
        this.listAbsence = listAbsence;
    }
}
