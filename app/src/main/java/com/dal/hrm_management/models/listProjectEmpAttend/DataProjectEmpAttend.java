package com.dal.hrm_management.models.listProjectEmpAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataProjectEmpAttend {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private ArrayList<ProjectAndProcess> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess> getList() {
        return list;
    }

    public void setList(ArrayList<com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess> list) {
        this.list = list;
    }
}

