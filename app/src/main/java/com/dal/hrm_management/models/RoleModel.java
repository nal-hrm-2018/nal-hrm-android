package com.dal.hrm_management.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleModel {
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("role_name")
    @Expose
    private String roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
