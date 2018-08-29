package com.dal.hrm_management.models.manageAbsence.hr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr> ListAbsenceForHr = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr> getList() {
        return ListAbsenceForHr;
    }

    public void setList(java.util.List<com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr> list) {
        this.ListAbsenceForHr = list;
    }

}