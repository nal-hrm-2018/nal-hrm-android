package com.dal.hrm_management.views.employee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicEmployeeFragment extends Fragment {


    private ImageView imv_avatar;
    private TextView tv_name;
    private TextView tv_role;
    private TextView tv_email;
    private TextView tv_address;
    private TextView tv_phone;
    private TextView tv_gender;
    private TextView tv_maritalStatus;
    private TextView tv_birthday ;
    private TextView tv_position ;
    private TextView tv_start ;
    private TextView tv_end ;

    public BasicEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_employee, container, false);
        initUI(view);
        loadData();
        setValueProfile();
        return view;
    }

    private void loadData() {
        //TO DO: get data
    }

    private void setValueProfile(){
        //TO DO: set value for UI Component

    }
    private void initUI(View view) {
        imv_avatar = (ImageView) view.findViewById(R.id.imv_avatar);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_role = (TextView) view.findViewById(R.id.tv_role);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_gender= (TextView) view.findViewById(R.id.tv_gender);
        tv_maritalStatus= (TextView) view.findViewById(R.id.tv_maritalStatus);
        tv_birthday = (TextView) view.findViewById(R.id.tv_birthday);
        tv_position = (TextView) view.findViewById(R.id.tv_position);
        tv_start = (TextView) view.findViewById(R.id.tv_start);
        tv_end = (TextView) view.findViewById(R.id.tv_end);
    }

}
