package com.dal.hrm_management.presenters.managerOvertime.status;

import okhttp3.RequestBody;

public interface IUpdateStatusPresenter {
    public void updateStatusOvertime(Long idOvertime, RequestBody json);
}
