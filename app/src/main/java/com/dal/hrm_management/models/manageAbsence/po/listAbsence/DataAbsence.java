package com.dal.hrm_management.models.manageAbsence.po.listAbsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public class DataAbsence {


    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Absence> absence = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataAbsence() {
    }


    public DataAbsence(Integer total, List<Absence> absence) {
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
