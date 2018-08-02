package com.dal.hrm_management;

import android.util.Log;
import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {
    String email;
    String password;


    public Login() {
    }

    public Login(String email) {
        this.email = email;
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;

    }

    public void validatorEmail() {

    }

    public void validatorPassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkNotNullEmail() {
        return email.length() > 0;
    }

    public boolean checkEmailContainA() {
        return email.contains("@");
    }

    public boolean checkEmailContainSpace() {
        return !email.contains(" ");
    }

    public boolean checkPasswordNotNull() {
        return password.length()>0;
    }

    public boolean checkEmailContainSpecialChar() {
        return false;
    }

    public boolean checkEmailOrPassNull() {
        return email.length()>0 && password.length()>0;
    }

    public boolean kiemTraEmailHopLe() {
        if (email == null) {
            return false;
        } else {
            return validate(email);
        }

//        Log.d("hihi", String.valueOf(Patterns.EMAIL_ADDRESS.matcher(email).matches()));
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
