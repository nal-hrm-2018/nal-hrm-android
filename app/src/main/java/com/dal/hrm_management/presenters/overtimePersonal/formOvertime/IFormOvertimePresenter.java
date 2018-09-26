package com.dal.hrm_management.presenters.overtimePersonal.formOvertime;

import okhttp3.RequestBody;

public interface IFormOvertimePresenter {
    public void getListProject();
    public void addOvertime(RequestBody body);
    public void editOvertime(RequestBody body, Long idOvertime);
}
