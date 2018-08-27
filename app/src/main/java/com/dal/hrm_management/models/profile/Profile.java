package com.dal.hrm_management.models.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile {

    @SerializedName("idEmployee")
    @Expose
    private Integer idEmployee;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nameEmployee")
    @Expose
    private String nameEmployee;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("genderDTO")
    @Expose
    private GenderDTO genderDTO;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("maritalStatusDTO")
    @Expose
    private MaritalStatusDTO maritalStatusDTO;
    @SerializedName("startWorkDate")
    @Expose
    private String startWorkDate;
    @SerializedName("endWorkDate")
    @Expose
    private String endWorkDate;
    @SerializedName("curriculum_vitae")
    @Expose
    private Object curriculumVitae;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("avatar")
    @Expose
    private Object avatar;
    @SerializedName("employeeType")
    @Expose
    private EmployeeType employeeType;
    @SerializedName("role")
    @Expose
    private Role role;
    @SerializedName("isManager")
    @Expose
    private Integer isManager;
    @SerializedName("salaryId")
    @Expose
    private Integer salaryId;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("permissions")
    @Expose
    private List<Permission> permissions = null;
    @SerializedName("teams")
    @Expose
    private List<Team> teams = null;
    @SerializedName("employee")
    @Expose
    private Boolean employee;

    /**
     * No args constructor for use in serialization
     *
     */
    public Profile() {
    }

    /**
     *
     * @param employeeType
     * @param birthday
     * @param endWorkDate
     * @param idEmployee
     * @param avatar
     * @param startWorkDate
     * @param updatedAt
     * @param teams
     * @param genderDTO
     * @param email
     * @param nameEmployee
     * @param address
     * @param createdAt
     * @param salaryId
     * @param maritalStatusDTO
     * @param company
     * @param permissions
     * @param role
     * @param isManager
     * @param employee
     * @param curriculumVitae
     * @param mobile
     */
    public Profile(Integer idEmployee, String email, String nameEmployee, String birthday, GenderDTO genderDTO, String mobile, String address, MaritalStatusDTO maritalStatusDTO, String startWorkDate, String endWorkDate, Object curriculumVitae, Object company, Object avatar, EmployeeType employeeType, Role role, Integer isManager, Integer salaryId, Object updatedAt, String createdAt, List<Permission> permissions, List<Team> teams, Boolean employee) {
        super();
        this.idEmployee = idEmployee;
        this.email = email;
        this.nameEmployee = nameEmployee;
        this.birthday = birthday;
        this.genderDTO = genderDTO;
        this.mobile = mobile;
        this.address = address;
        this.maritalStatusDTO = maritalStatusDTO;
        this.startWorkDate = startWorkDate;
        this.endWorkDate = endWorkDate;
        this.curriculumVitae = curriculumVitae;
        this.company = company;
        this.avatar = avatar;
        this.employeeType = employeeType;
        this.role = role;
        this.isManager = isManager;
        this.salaryId = salaryId;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.permissions = permissions;
        this.teams = teams;
        this.employee = employee;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public GenderDTO getGenderDTO() {
        return genderDTO;
    }

    public void setGenderDTO(GenderDTO genderDTO) {
        this.genderDTO = genderDTO;
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

    public MaritalStatusDTO getMaritalStatusDTO() {
        return maritalStatusDTO;
    }

    public void setMaritalStatusDTO(MaritalStatusDTO maritalStatusDTO) {
        this.maritalStatusDTO = maritalStatusDTO;
    }

    public String getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(String startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public String getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(String endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public Object getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(Object curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public String getUpdatedAt() {
        return String.valueOf(updatedAt);
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public Boolean getEmployee() {
        return employee;
    }

    public void setEmployee(Boolean employee) {
        this.employee = employee;
    }
}