package com.dal.hrm_management.views.list_employee;

import com.dal.hrm_management.models.listEmployee.ListEmployees;

import java.util.List;

public interface IListEmployee {
    public void Success(List<ListEmployees> listEmployee);
    public void getListEmployeeFailure();

}
