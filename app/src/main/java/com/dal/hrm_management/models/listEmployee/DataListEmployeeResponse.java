package com.dal.hrm_management.models.listEmployee;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class DataListEmployeeResponse {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private java.util.List<Employee> list = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public java.util.List<Employee> getList() {
        return list;
    }

    public void setList(java.util.List<Employee> list) {
        this.list = list;
    }
    
}
