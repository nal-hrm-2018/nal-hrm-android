package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.Overtime;
import java.util.List;

public class DataOvertime {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Overtime> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Overtime> getList() {
        return list;
    }

    public void setList(List<Overtime> list) {
        this.list = list;
    }

}