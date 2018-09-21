package com.dal.hrm_management.models.manageOvertime.po.viewlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<ItemOverTimePO> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<ItemOverTimePO> getList() {
        return list;
    }

    public void setList(java.util.List<ItemOverTimePO> list) {
        this.list = list;
    }

}