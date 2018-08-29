package com.dal.hrm_management.presenters.absence;


import org.json.JSONObject;

import okhttp3.RequestBody;

public interface IAbsencePresenter {
    public void getDataAbsence(int currentPage, int pageSize);
    public void addAbsence(RequestBody json);
    public void getTypeAbsence();
}
