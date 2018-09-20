package com.dal.hrm_management.models.manageOvertime.hr.viewList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

public class DataOvertime {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Overtime> overtime = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Overtime> getOvertime() {
        return overtime;
    }

    public void setOvertime(List<Overtime> overtime) {
        this.overtime = overtime;
    }

}