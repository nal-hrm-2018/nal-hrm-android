package com.dal.hrm_management.views.employee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dal.hrm_management.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectEmployeeFragment extends Fragment {


    public ProjectEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_project_employee, container, false);
    }

}
