package com.dal.hrm_management.models.absence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 24-Aug-18.
 */
public class DataAbsence {

    @SerializedName("allowAbsence")
    @Expose
    private int allowAbsence;
    @SerializedName("remainingAbsenceDays")
    @Expose
    private int remainingAbsenceDays;
    @SerializedName("annualLeave")
    @Expose
    private int annualLeave;
    @SerializedName("unpaidLeave")
    @Expose
    private int unpaidLeave;
    @SerializedName("marriageLeave")
    @Expose
    private int marriageLeave;
    @SerializedName("bereavementLeave")
    @Expose
    private int bereavementLeave;
    @SerializedName("maternityLeave")
    @Expose
    private int maternityLeave;
    @SerializedName("listAbsence")
    @Expose
    private ListAbsence listAbsence;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataAbsence() {
    }

    /**
     *
     * @param annualLeave
     * @param remainingAbsenceDays
     * @param bereavementLeave
     * @param marriageLeave
     * @param maternityLeave
     * @param unpaidLeave
     * @param listAbsence
     * @param allowAbsence
     */
    public DataAbsence(int allowAbsence, int remainingAbsenceDays, int annualLeave, int unpaidLeave, int marriageLeave, int bereavementLeave, int maternityLeave, ListAbsence listAbsence) {
        super();
        this.allowAbsence = allowAbsence;
        this.remainingAbsenceDays = remainingAbsenceDays;
        this.annualLeave = annualLeave;
        this.unpaidLeave = unpaidLeave;
        this.marriageLeave = marriageLeave;
        this.bereavementLeave = bereavementLeave;
        this.maternityLeave = maternityLeave;
        this.listAbsence = listAbsence;
    }

    public int getAllowAbsence() {
        return allowAbsence;
    }

    public void setAllowAbsence(int allowAbsence) {
        this.allowAbsence = allowAbsence;
    }

    public int getRemainingAbsenceDays() {
        return remainingAbsenceDays;
    }

    public void setRemainingAbsenceDays(int remainingAbsenceDays) {
        this.remainingAbsenceDays = remainingAbsenceDays;
    }

    public int getAnnualLeave() {
        return annualLeave;
    }

    public void setAnnualLeave(int annualLeave) {
        this.annualLeave = annualLeave;
    }

    public int getUnpaidLeave() {
        return unpaidLeave;
    }

    public void setUnpaidLeave(int unpaidLeave) {
        this.unpaidLeave = unpaidLeave;
    }

    public int getMarriageLeave() {
        return marriageLeave;
    }

    public void setMarriageLeave(int marriageLeave) {
        this.marriageLeave = marriageLeave;
    }

    public int getBereavementLeave() {
        return bereavementLeave;
    }

    public void setBereavementLeave(int bereavementLeave) {
        this.bereavementLeave = bereavementLeave;
    }

    public int getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(int maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public ListAbsence getListAbsence() {
        return listAbsence;
    }

    public void setListAbsence(ListAbsence listAbsence) {
        this.listAbsence = listAbsence;
    }

}