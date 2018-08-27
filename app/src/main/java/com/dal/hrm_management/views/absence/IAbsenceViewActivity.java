package com.dal.hrm_management.views.absence;

import com.dal.hrm_management.models.absence.DataAbsence;

/**
 * Created by Luu Ngoc Lan on 24-Aug-18.
 */

public interface IAbsenceViewActivity {

    public void getAbsencePersonalSuccess(DataAbsence dataAbsence);

    public void getAbsencePersonalFailed();
}
