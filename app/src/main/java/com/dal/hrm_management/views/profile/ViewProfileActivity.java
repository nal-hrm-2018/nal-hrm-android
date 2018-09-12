package com.dal.hrm_management.views.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dal.hrm_management.R;
import com.dal.hrm_management.api.ApiImageClient;
import com.dal.hrm_management.api.RetrofitImageAPI;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.presenters.profile.ProfilePresenter;
import com.dal.hrm_management.utils.StringUtils;

import java.util.List;

import okhttp3.Credentials;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProfileActivity extends AppCompatActivity implements IProfileActivity {

    private ImageView imv_avatar;
    private TextView tv_name;
    private TextView tv_position;
    private TextView tv_email;
    private TextView tv_address;
    private TextView tv_phone;
    private TextView tv_gender;
    private TextView tv_maritalStatus;
    private TextView tv_birthday;
    private TextView tv_type;
    private TextView tv_team;
    private TextView tv_start;
    private TextView tv_end;
    private Button btn_changePassword;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;
    private ProfilePresenter profilePresenter;

    String uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        displayBackHome();
        initPresenter();
        initUI();
        getDataFromServer();
        addEventOnUI();

    }

    private void getDataFromServer() {
        profilePresenter.getProfile(LoginPresenter.token);
    }

    private void initPresenter() {
        profilePresenter = new ProfilePresenter(this);
    }

    private void addEventOnUI() {
        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TO DO: change password
            }
        });
    }

    private void initUI() {
        imv_avatar = (ImageView) findViewById(R.id.imv_avatar);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_maritalStatus = (TextView) findViewById(R.id.tv_maritalStatus);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_type = (TextView) findViewById(R.id.tv_type);
        tv_team = (TextView) findViewById(R.id.tv_team);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        btn_changePassword = (Button) findViewById(R.id.btn_changepassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private void displayBackHome() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        //set white color for icon_edit
        Drawable icon_edit = menu.getItem(0).getIcon();
        icon_edit.mutate();
        icon_edit.setColorFilter(getResources().getColor(R.color.color_white), PorterDuff.Mode.SRC_IN);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_edit:
                Intent intent = new Intent(this, EditProfileActivity.class);
                startActivityForResult(intent, 200);
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getProfileSuccess(Profile profile) {
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
                        Log.d("DATA", response.body().toString());
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        imv_avatar.setImageBitmap(bitmap);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
        if (profile.getNameEmployee() != null) {
            tv_name.setText(profile.getNameEmployee());
        } else tv_name.setText(R.string.infor_null);
        if (profile.getRole().getNameRole() != null) {
            tv_position.setText(profile.getRole().getNameRole());
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
        if (profile.getGenderDTO().getNameGender() != null) {
            tv_gender.setText(profile.getGenderDTO().getNameGender());
        } else tv_gender.setText(R.string.infor_null);
        if (profile.getMaritalStatusDTO().getTypeMaritalStatus() != null) {
            tv_maritalStatus.setText(profile.getMaritalStatusDTO().getTypeMaritalStatus());
        } else tv_maritalStatus.setText(R.string.infor_null);
        if (profile.getBirthday() != null) {
            tv_birthday.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(profile.getBirthday()));
        } else tv_birthday.setText(R.string.infor_null);
        if (profile.getEmployeeType() != null) {
            String type = profile.getEmployeeType().getNameEmployeeType();
            if (type.toLowerCase().equals("hr")) {
                tv_position.setBackgroundColor(getResources().getColor(R.color.color_red));
            } else if (type.toLowerCase().equals("po")) {
                tv_position.setBackgroundColor(getResources().getColor(R.color.color_orange));
            } else if (type.toLowerCase().equals("dev")) {
                tv_position.setBackgroundColor(getResources().getColor(R.color.color_green));
            } else if (type.toLowerCase().equals("accountant")) {
                tv_position.setBackgroundColor(getResources().getColor(R.color.color_gray));
            }
            tv_type.setText(profile.getEmployeeType().getNameEmployeeType());
        } else tv_type.setText(R.string.infor_null);
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
            tv_start.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(profile.getStartWorkDate()));
        } else tv_start.setText(R.string.infor_null);
        if (profile.getEndWorkDate() != null) {
            tv_end.setText(StringUtils.yyyy_mm_ddTodd_mm_yyyy(profile.getEndWorkDate()));
        } else tv_end.setText(R.string.infor_null);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void getProfileFailure() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            getDataFromServer();
        }
    }

}
