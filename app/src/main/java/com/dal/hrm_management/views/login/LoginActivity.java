package com.dal.hrm_management.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dal.hrm_management.R;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.home.HomePageActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginActivity {
    private EditText edt_email;
    private EditText edt_password;
    private Button btn_login;
    private SharedPreferences sharedPreferences;
    private CheckBox cb_remeber;
    private int PASSWORD_LENGTH = 6;
    private ProgressDialog progressDialog;
    LoginPresenter loginPresenter;

    public LoginActivity() {

    }

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
        edt_email.setOnClickListener(this);
        edt_password.setOnClickListener(this);
        btn_login = findViewById(R.id.btn_login);
        cb_remeber = findViewById(R.id.cb_remember);
        sharedPreferences = getSharedPreferences("remeberMe", MODE_PRIVATE);
        initProgressProcess();
    }

    private void initProgressProcess() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Signing");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                btn_login.setEnabled(false);
                if (isConnected()) {
                    progressDialog.show();
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
                } else {
                    Toast toast= Toast.makeText(getApplicationContext(),
                            "No internet", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                break;
            case R.id.edt_email:
            case R.id.edt_password:
                progressDialog.dismiss();
                break;
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
            btn_login.setEnabled(true);
            progressDialog.dismiss();
        } else {
            //success
            loginPresenter.getToken(email, pass);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@") && email.contains(".");
    }

    @Override
    public void loginSucess(String token) {
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
        btn_login.setEnabled(true);
        progressDialog.dismiss();
        finish();
    }

    @Override
    public void loginFailure() {
        edt_password.setError(getString(R.string.error_incorrect_password));
        edt_password.requestFocus();
        btn_login.setEnabled(true);
        progressDialog.dismiss();
    }

    @Override
    public void errorInServer() {
        progressDialog.dismiss();
        Toast toast= Toast.makeText(getApplicationContext(),
                "Server Error", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
        btn_login.setEnabled(true);
    }

    /**
     * Kiểm tra có kết nối internet hay không
     * @return trạng thái kết nối internet
     */
    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
