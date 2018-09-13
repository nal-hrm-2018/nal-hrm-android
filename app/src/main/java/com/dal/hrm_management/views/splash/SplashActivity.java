package com.dal.hrm_management.views.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dal.hrm_management.R;
import com.dal.hrm_management.presenters.home.HomePresenter;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.home.HomePageActivity;
import com.dal.hrm_management.views.home.iHomeActivity;
import com.dal.hrm_management.views.login.ILoginActivity;
import com.dal.hrm_management.views.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean isLoggingIn;
    private String emailSaved, passwordSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        CheckThread checkThread = new CheckThread();
        checkThread.start();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("remeberMe", MODE_PRIVATE);
        isLoggingIn = sharedPreferences.getBoolean("isLoggingIn", false);
        emailSaved = sharedPreferences.getString("email", "");
        passwordSaved = sharedPreferences.getString("password", "");
    }

    public class CheckThread extends Thread implements ILoginActivity, iHomeActivity {
        @Override
        public void run() {
            super.run();
            try {
                // NOTE: Sleep 2s to take slow down checkLogin for show Splash
                sleep(2000);
                if (isLoginAlready()) {
                    if (!emailSaved.equals("") && !passwordSaved.equals("")) {
                        LoginPresenter loginPresenter = new LoginPresenter(this);
                        loginPresenter.getToken(emailSaved, passwordSaved);
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void loginSucess(String token) {
            HomePresenter homePresenter = new HomePresenter(this);
            homePresenter.getProfile(token);
        }

        @Override
        public void loginFailure() {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void errorInServer() {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        @Override
        public void Success() {
            Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
            startActivity(intent);
        }

        @Override
        public void Failure() {

        }
    }

    /**
     * check user have logged out yet
     *
     * @return
     */
    private boolean isLoginAlready() {
        if (isLoggingIn) {
            return true;
        }
        return false;
    }
}
