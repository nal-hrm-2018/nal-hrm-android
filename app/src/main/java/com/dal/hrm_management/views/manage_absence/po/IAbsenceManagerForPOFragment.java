package com.dal.hrm_management.views.manage_absence.po;

import com.dal.hrm_management.models.manageAbsence.po.listAbsence.DataAbsence;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public interface IAbsenceManagerForPOFragment {

    public void getListProjectInProgressSuccess(DataProject data);

    public void getListProjectInProgressFailed();

    public void getListAbsenceInProjectSuccess(DataAbsence dataAbsence);

    public void getListAbsenceInProjectFailed();
}
