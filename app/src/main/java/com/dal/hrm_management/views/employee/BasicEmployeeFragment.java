package com.dal.hrm_management.views.employee;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.api.ApiImageClient;
import com.dal.hrm_management.api.RetrofitImageAPI;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.presenters.employee.BasicEmployeePresenter;

import java.util.List;

import okhttp3.Credentials;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        RetrofitImageAPI retrofitImageAPI = ApiImageClient.getImageClient().create(RetrofitImageAPI.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ResponseBody> call;
        if (profile.getAvatar() != null) {
            call = retrofitImageAPI.getImageDetails(auth, profile.getAvatar().toString());
        } else {
            call = retrofitImageAPI.getImageDetails(auth, "default_avatar.png");
        }
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        imv_avatar.setImageBitmap(bitmap);
                    } else {
                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
        getActivity().setTitle(profile.getNameEmployee());
        if (profile.getNameEmployee() != null) {
            tv_name.setText(profile.getNameEmployee());
        } else tv_name.setText(R.string.infor_null);
        if (profile.getRole().getNameRole() != null) {
            String role = profile.getRole().getNameRole();
            if (role.toLowerCase().equals("hr")) {
                tv_role.setBackgroundColor(getResources().getColor(R.color.color_red));
            } else if (role.toLowerCase().equals("po")) {
                tv_role.setBackgroundColor(getResources().getColor(R.color.color_orange));
            } else if (role.toLowerCase().equals("dev")) {
                tv_role.setBackgroundColor(getResources().getColor(R.color.color_green));
            } else if (role.toLowerCase().equals("accountant")) {
                tv_role.setBackgroundColor(getResources().getColor(R.color.color_gray));
            }
            tv_role.setText(role);
        } else tv_position.setText(R.string.infor_null);
        if (profile.getEmail() != null) {
            tv_email.setText(profile.getEmail());
        } else tv_email.setText(R.string.infor_null);
        if (profile.getAddress() != null) {
            tv_address.setText(profile.getAddress());
        } else tv_address.setText(R.string.infor_null);
        if (profile.getMobile() != null) {
            tv_phone.setText(profile.getMobile());
        } else tv_phone.setText(R.string.infor_null);
        if (profile.getGenderDTO() != null) {
            tv_gender.setText(profile.getGenderDTO().getNameGender());
        } else tv_gender.setText(R.string.infor_null);
        if (profile.getMaritalStatusDTO() != null) {
            tv_maritalStatus.setText(profile.getMaritalStatusDTO().getTypeMaritalStatus());
        } else tv_maritalStatus.setText(R.string.infor_null);
        if (profile.getBirthday() != null) {
            tv_birthday.setText(profile.getBirthday());
        } else tv_birthday.setText(R.string.infor_null);
        if (profile.getEmployeeType() != null) {
            tv_position.setText(profile.getEmployeeType().getNameEmployeeType());
        } else tv_position.setText(R.string.infor_null);
        List<Team> teamList = profile.getTeams();
        if (teamList.size() != 0) {
            for (int i = 0; i < teamList.size() - 1; i++) {
                tv_team.setText(tv_team.getText() + teamList.get(i).getNameTeam() + ", ");
            }
            tv_team.setText(tv_team.getText() + teamList.get(teamList.size() - 1).getNameTeam());
        } else {
            tv_team.setText(R.string.infor_null);
        }
        if (profile.getStartWorkDate() != null) {
            tv_start.setText(profile.getStartWorkDate());
        } else tv_start.setText(R.string.infor_null);
        if (profile.getEndWorkDate() != null) {
            tv_end.setText(profile.getEndWorkDate());
        } else tv_end.setText(R.string.infor_null);
    }

    @Override
    public void getBasicEmployeeFailed() {
        Toast.makeText(getActivity(), "Not get data", Toast.LENGTH_SHORT).show();
    }
}
