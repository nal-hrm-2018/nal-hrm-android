package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOvertime {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime> getList() {
        return list;
    }

    public void setList(java.util.List<com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime> list) {
        this.list = list;
    }

}