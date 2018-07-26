package com.dal.hrm_management.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dal.hrm_management.R;
import com.dal.hrm_management.views.profile.ViewProfileActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
