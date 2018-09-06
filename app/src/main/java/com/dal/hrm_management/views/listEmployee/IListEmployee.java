package com.dal.hrm_management.views.listEmployee;

import com.dal.hrm_management.models.listEmployee.Employee;

import java.util.List;

public interface IListEmployee {
    public void Success(List<Employee> listEmployee);
    public void getListEmployeeFailure();

}
