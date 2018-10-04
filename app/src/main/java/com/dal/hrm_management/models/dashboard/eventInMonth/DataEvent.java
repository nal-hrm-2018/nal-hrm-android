package com.dal.hrm_management.models.dashboard.eventInMonth;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEvent {
    @SerializedName("newEmployees")
    @Expose
    private Integer newEmployees;
    @SerializedName("birthdays")
    @Expose
    private Integer birthdays;
    @SerializedName("employeesQuit")
    @Expose
    private Integer employeesQuit;

    public Integer getNewEmployees() {
        return newEmployees;
    }

    public void setNewEmployees(Integer newEmployees) {
        this.newEmployees = newEmployees;
    }

    public Integer getBirthdays() {
        return birthdays;
    }

    public void setBirthdays(Integer birthdays) {
        this.birthdays = birthdays;
    }

    public Integer getEmployeesQuit() {
        return employeesQuit;
    }

    public void setEmployeesQuit(Integer employeesQuit) {
        this.employeesQuit = employeesQuit;
    }
}
