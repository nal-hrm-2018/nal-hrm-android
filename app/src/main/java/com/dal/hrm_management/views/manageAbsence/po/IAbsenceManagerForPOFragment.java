package com.dal.hrm_management.views.manageAbsence.po;

import com.dal.hrm_management.models.manageAbsence.po.listAbsence.DataAbsence;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;

public interface IAbsenceManagerForPOFragment {

    public void getListProjectInProgressSuccess(DataProject data);

    public void getListProjectInProgressFailed();

    public void getListAbsenceInProjectSuccess(DataAbsence dataAbsence);

    public void getListAbsenceInProjectFailed();
}
