package com.dal.hrm_management.views.absenceEmployee;

import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Data;

/**
 * Created by Luu Ngoc Lan on 21-Aug-18.
 */

public interface IDetailAbsenceEmployeeActivity {
    public void getDetailAbsenceEmployeeSuccess(Data dataAbsence);

    public void getDetailAbsenceEmployeeFailed();


}
