package com.dal.hrm_management.views.employee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.presenters.employee.BasicEmployeePresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BasicEmployeeFragment extends Fragment implements IBasicEmployeeFragment {


    private ImageView imv_avatar;
    private TextView tv_name;
    private TextView tv_role;
    private TextView tv_email;
    private TextView tv_address;
    private TextView tv_phone;
    private TextView tv_gender;
    private TextView tv_maritalStatus;
    private TextView tv_birthday;
    private TextView tv_position;
    private TextView tv_team;
    private TextView tv_start;
    private TextView tv_end;
    public static int id_employee;

    private BasicEmployeePresenter basicEmployeePresenter;

    public BasicEmployeeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_employee, container, false);
        initUI(view);
        getDataFromIntent();
        initPresenter();
        return view;
    }

    private void initPresenter() {
        basicEmployeePresenter = new BasicEmployeePresenter(this);
        basicEmployeePresenter.getProfileEmployee(id_employee);
    }

    private void getDataFromIntent() {
        id_employee = getActivity().getIntent().getIntExtra("id_employee", -1);
    }

    private void loadData() {
        //TO DO: get data
    }

    private void initUI(View view) {

        imv_avatar = (ImageView) view.findViewById(R.id.imv_avatar);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_role = (TextView) view.findViewById(R.id.tv_position);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_gender = (TextView) view.findViewById(R.id.tv_gender);
        tv_maritalStatus = (TextView) view.findViewById(R.id.tv_maritalStatus);
        tv_birthday = (TextView) view.findViewById(R.id.tv_birthday);
        tv_position = (TextView) view.findViewById(R.id.tv_type);
        tv_team = (TextView) view.findViewById(R.id.tv_team);
        tv_start = (TextView) view.findViewById(R.id.tv_start);
        tv_end = (TextView) view.findViewById(R.id.tv_end);
    }

    @Override
    public void getBasicEmployeeSuccess(Profile profile) {
        getActivity().setTitle(profile.getNameEmployee());
        tv_name.setText(profile.getNameEmployee());
        tv_role.setText(profile.getRole().getNameRole());
        tv_email.setText(profile.getEmail());
        tv_address.setText(profile.getAddress());
        tv_phone.setText(profile.getMobile());
        tv_gender.setText(profile.getGenderDTO().getNameGender());
        tv_maritalStatus.setText(profile.getMaritalStatusDTO().getTypeMaritalStatus());
        tv_birthday.setText(profile.getBirthday());
        tv_position.setText(profile.getEmployeeType().getNameEmployeeType());
        List<Team> teamList = profile.getTeams();
        for (int i = 0; i < teamList.size() - 1; i++) {
            tv_team.setText(tv_team.getText() + teamList.get(i).getNameTeam() + ", ");
        }
        tv_team.setText(tv_team.getText()+teamList.get(teamList.size()-1).getNameTeam());
        tv_start.setText(profile.getStartWorkDate());
        tv_end.setText(profile.getEndWorkDate());
    }

    @Override
    public void getBasicEmployeeFailed() {

    }
}
