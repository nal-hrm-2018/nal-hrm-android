package com.dal.hrm_management.views.manageAbsence.Hr.holiday;

import com.dal.hrm_management.models.holiday.Holiday;

import java.util.List;

public interface IHolidayFragment {
    public void getHolidaySuccess(List<Holiday> data);
    public void getHolidayFailure();
}
