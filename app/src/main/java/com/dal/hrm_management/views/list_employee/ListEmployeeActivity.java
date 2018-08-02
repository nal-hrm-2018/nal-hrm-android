package com.dal.hrm_management.views.list_employee;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.presenters.ListEmployee.ListEmployeePresenter;

public class ListEmployeeActivity extends Fragment implements IListEmployeeActivity{
    private static final String TAG = ListEmployeeActivity.class.getName();
    ListEmployeePresenter listEmployeePresenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapMVP();
        listEmployeePresenter.getListEmployee();
        return inflater.inflate(R.layout.fragment_list_emp,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void mapMVP() {
        listEmployeePresenter = new ListEmployeePresenter(this);
    }

    @Override
    public void getListEmployeeFailure() {
        Log.d(TAG,"failure");

    }
}
