package com.dal.hrm_management.views.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.dal.hrm_management.R;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.home.HomePage;
import com.dal.hrm_management.views.profile.ViewProfileActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,ILoginActivity {
    //declare view
    private EditText edt_email;
    private EditText edt_password;
    private Button btn_login;
    private SharedPreferences sharedPreferences;
    private CheckBox cb_remeber;
    private ProgressBar progressBar;
    //process
    private int PASSWORD_LENGTH = 6;
    //mvp
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapMVP();
        mapViewWithActivity();
        loadSharedPreferences();
        btn_login.setOnClickListener(this);

    }

    private void mapMVP() {
        loginPresenter = new LoginPresenter(this);
    }

    private void loadSharedPreferences() {
        String email = sharedPreferences.getString("email", null);
        String pass = sharedPreferences.getString("password", null);
        if (email != null) {
            cb_remeber.setChecked(true);
            edt_email.setText(email);
            edt_password.setText(pass);
        }
    }


    private void mapViewWithActivity() {
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        cb_remeber = findViewById(R.id.cb_remember);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        sharedPreferences = getSharedPreferences("remeberMe", MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_login){
            progressBar.setVisibility(View.VISIBLE);
            if (cb_remeber.isChecked()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("remeber", true);
                editor.putString("email", edt_email.getText().toString());
                editor.putString("password", edt_password.getText().toString());
                editor.commit();
            } else {
                sharedPreferences.edit().clear().commit();
            }
            checkLogin();
        }
    }

    private void checkLogin() {
        edt_email.setError(null);
        edt_password.setError(null);
        boolean cancel = false;
        View focusView = null;
        String email = edt_email.getText().toString();
        String pass = edt_password.getText().toString();
        //valid pass
        if (TextUtils.isEmpty(pass)) {
            edt_password.setError(getString(R.string.catch_invalid_password_empty));
            focusView = edt_password;
            cancel = true;
        } else if (pass.length() < PASSWORD_LENGTH) {
            edt_password.setError(getString(R.string.catch_invalid_password_length));
            focusView = edt_password;
            cancel = true;
        }
        //Valid email;
        if (TextUtils.isEmpty(email)) {
            edt_email.setError(getString(R.string.catch_invalid_email_empty));
            focusView = edt_email;
            cancel = true;
        } else if (!isEmailValid(email)) {
            edt_email.setError(getString(R.string.edt_check_error_invalid_email));
            focusView = edt_email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            //success

            loginPresenter.getToken(email,pass);
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".");
    }

    @Override
    public void loginSucess() {
        Intent intent = new Intent(LoginActivity.this, HomePage.class);
        startActivity(intent);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loginFailure() {
        edt_password.setError(getString(R.string.error_incorrect_password));
        edt_password.requestFocus();
        progressBar.setVisibility(View.INVISIBLE);
    }
}
