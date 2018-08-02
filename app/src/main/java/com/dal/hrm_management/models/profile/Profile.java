package com.dal.hrm_management.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luu Ngoc Lan on 30-Jul-18.
 */

public class Profile {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("marital_status")
    @Expose
    private Integer maritalStatus;
    @SerializedName("startwork_date")
    @Expose
    private String startworkDate;
    @SerializedName("endwork_date")
    @Expose
    private String endworkDate;
    @SerializedName("curriculum_vitae")
    @Expose
    private String curriculumVitae;
    @SerializedName("is_employee")
    @Expose
    private Integer isEmployee;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("employee_type_id")
    @Expose
    private Integer employeeTypeId;
    @SerializedName("team_id")
    @Expose
    private Integer teamId;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("is_manager")
    @Expose
    private Integer isManager;
    @SerializedName("salary_id")
    @Expose
    private Object salaryId;
    @SerializedName("work_status")
    @Expose
    private Integer workStatus;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("updated_by_employee")
    @Expose
    private Object updatedByEmployee;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("created_by_employee")
    @Expose
    private Object createdByEmployee;
    @SerializedName("delete_flag")
    @Expose
    private Integer deleteFlag;

    /**
     * No args constructor for use in serialization
     *
     */
    public Profile() {
    }

    /**
     *
     * @param employeeTypeId
     * @param birthday
     * @param createdByEmployee
     * @param updatedByEmployee
     * @param startworkDate
     * @param avatar
     * @param maritalStatus
     * @param teamId
     * @param updatedAt
     * @param id
     * @param email
     * @param address
     * @param createdAt
     * @param salaryId
     * @param company
     * @param name
     * @param gender
     * @param workStatus
     * @param isManager
     * @param deleteFlag
     * @param isEmployee
     * @param curriculumVitae
     * @param roleId
     * @param mobile
     * @param endworkDate
     */
    public Profile(Integer id, String email, String name, String birthday, Integer gender, String mobile, String address, Integer maritalStatus, String startworkDate, String endworkDate, String curriculumVitae, Integer isEmployee, Object company, String avatar, Integer employeeTypeId, Integer teamId, Integer roleId, Integer isManager, Object salaryId, Integer workStatus, String updatedAt, Object updatedByEmployee, Object createdAt, Object createdByEmployee, Integer deleteFlag) {
        super();
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
        this.maritalStatus = maritalStatus;
        this.startworkDate = startworkDate;
        this.endworkDate = endworkDate;
        this.curriculumVitae = curriculumVitae;
        this.isEmployee = isEmployee;
        this.company = company;
        this.avatar = avatar;
        this.employeeTypeId = employeeTypeId;
        this.teamId = teamId;
        this.roleId = roleId;
        this.isManager = isManager;
        this.salaryId = salaryId;
        this.workStatus = workStatus;
        this.updatedAt = updatedAt;
        this.updatedByEmployee = updatedByEmployee;
        this.createdAt = createdAt;
        this.createdByEmployee = createdByEmployee;
        this.deleteFlag = deleteFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getStartworkDate() {
        return startworkDate;
    }

    public void setStartworkDate(String startworkDate) {
        this.startworkDate = startworkDate;
    }

    public String getEndworkDate() {
        return endworkDate;
    }

    public void setEndworkDate(String endworkDate) {
        this.endworkDate = endworkDate;
    }

    public String getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public Integer getIsEmployee() {
        return isEmployee;
    }

    public void setIsEmployee(Integer isEmployee) {
        this.isEmployee = isEmployee;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getEmployeeTypeId() {
        return employeeTypeId;
    }

    public void setEmployeeTypeId(Integer employeeTypeId) {
        this.employeeTypeId = employeeTypeId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Object getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Object salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getUpdatedByEmployee() {
        return updatedByEmployee;
    }

    public void setUpdatedByEmployee(Object updatedByEmployee) {
        this.updatedByEmployee = updatedByEmployee;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getCreatedByEmployee() {
        return createdByEmployee;
    }

    public void setCreatedByEmployee(Object createdByEmployee) {
        this.createdByEmployee = createdByEmployee;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
