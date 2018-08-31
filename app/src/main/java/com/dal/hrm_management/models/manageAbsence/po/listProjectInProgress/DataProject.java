package com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public class DataProject {
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("list")
    @Expose
    private List<Project> project = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataProject() {
    }

    /**
     *
     * @param total
     * @param project
     */
    public DataProject(Integer total, List<Project> project) {
        super();
        this.total = total;
        this.project = project;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

}
