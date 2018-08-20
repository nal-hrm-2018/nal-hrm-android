package com.dal.hrm_management.models.listProjectEmpAttend;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Processes {

    @SerializedName("idProcesses")
    @Expose
    private Integer idProcesses;
    @SerializedName("employeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("projectId")
    @Expose
    private String projectId;
    @SerializedName("roleId")
    @Expose
    private Integer roleId;
    @SerializedName("checkProjectExit")
    @Expose
    private Object checkProjectExit;
    @SerializedName("man_power")
    @Expose
    private Double manPower;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("role")
    @Expose
    private Role role;

    public Integer getIdProcesses() {
        return idProcesses;
    }

    public void setIdProcesses(Integer idProcesses) {
        this.idProcesses = idProcesses;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Object getCheckProjectExit() {
        return checkProjectExit;
    }

    public void setCheckProjectExit(Object checkProjectExit) {
        this.checkProjectExit = checkProjectExit;
    }

    public Double getManPower() {
        return manPower;
    }

    public void setManPower(Double manPower) {
        this.manPower = manPower;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}