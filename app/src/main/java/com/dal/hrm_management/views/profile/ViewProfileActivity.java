package com.dal.hrm_management.views.profile;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dal.hrm_management.R;

public class ViewProfileActivity extends AppCompatActivity {

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
    private Button btn_changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        displayBackHome();
        initUI();
        addEventOnUI();
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
        tv_role = (TextView) findViewById(R.id.tv_role);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_gender= (TextView) findViewById(R.id.tv_gender);
        tv_maritalStatus= (TextView) findViewById(R.id.tv_maritalStatus);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_end = (TextView) findViewById(R.id.tv_end);
        btn_changePassword = (Button) findViewById(R.id.btn_changepassword);
    }

    private void displayBackHome() {
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_edit:
                //TO DO: Call edit_profile_activity
                break;
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "Comeback home", Toast.LENGTH_SHORT).show();
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
