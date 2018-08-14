package com.dal.hrm_management.presenters.absence;

import com.dal.hrm_management.models.fakeData.AbsenceModel;
import com.dal.hrm_management.views.absence.IAbsenceView;

import java.util.ArrayList;
import java.util.List;

public class AbsencePresenter implements IAbsencePresenter {
    private IAbsenceView iAbsenceView;
    public List<AbsenceModel> arr = new ArrayList<AbsenceModel>();


    public AbsencePresenter(IAbsenceView iAbsenceView) {
        this.iAbsenceView = iAbsenceView;
    }
    public void initData(){
        AbsenceModel absenceModel = new AbsenceModel("Nghỉ phép","03/08/2018","04/08/2018","thích","waiting","Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép","03/08/2018","04/08/2018","thích","waiting","Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép","03/08/2018","04/08/2018","thích","waiting","Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép","03/08/2018","04/08/2018","thích","waiting","Cả ngày");
        arr.add(absenceModel);
    }
}
