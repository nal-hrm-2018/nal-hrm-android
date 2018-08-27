package com.dal.hrm_management.models.absence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 24-Aug-18.
 */
public class ListAbsence {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Absence> absence = null;

    /**
     * No args constructor for use in serialization
     */
    public ListAbsence() {
    }

    /**
     * @param total
     * @param absence
     */
    public ListAbsence(Integer total, List<Absence> absence) {
        super();
        this.total = total;
        this.absence = absence;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Absence> getAbsence() {
        return absence;
    }

    public void setAbsence(List<Absence> absence) {
        this.absence = absence;
    }

}