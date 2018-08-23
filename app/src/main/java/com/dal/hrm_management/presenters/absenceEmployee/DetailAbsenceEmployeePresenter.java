package com.dal.hrm_management.presenters.absenceEmployee;

import com.dal.hrm_management.models.fakeData.AbsenceModel;
import com.dal.hrm_management.views.absenceEmployee.IDetailAbsenceEmployeeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luu Ngoc Lan on 21-Aug-18.
 */

public class DetailAbsenceEmployeePresenter {
    private IDetailAbsenceEmployeeActivity iDetailAbsenceEmployeeActivity;
    public List<AbsenceModel> arr = new ArrayList<>();


    public DetailAbsenceEmployeePresenter(IDetailAbsenceEmployeeActivity iDetailAbsenceEmployeeActivity) {
        this.iDetailAbsenceEmployeeActivity = iDetailAbsenceEmployeeActivity;
    }

    public void initData() {
        AbsenceModel absenceModel = new AbsenceModel("Nghỉ phép 1", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép 2", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép 3", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày");
        arr.add(absenceModel);
        absenceModel = new AbsenceModel("Nghỉ phép 4", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày");
        arr.add(absenceModel);
        arr.add(new AbsenceModel("Nghỉ phép", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày"));
        arr.add(new AbsenceModel("Nghỉ phép", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày"));
        arr.add(new AbsenceModel("Nghỉ phép", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày"));
        arr.add(new AbsenceModel("Nghỉ phép", "03/08/2018", "04/08/2018", "thích", "waiting", "Cả ngày"));
    }
}
