package com.huda.lecture6demos.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huda.lecture6demos.repo.FirebaseRepo;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<Boolean> isDataValid = new MutableLiveData<>();
    public MutableLiveData<String> loginSuccessful;
    public MutableLiveData<String> loginFailed;
    private FirebaseRepo repo = new FirebaseRepo();
    private String email;
    private String pass;

    public LoginViewModel() {
        loginSuccessful = repo.loginSuccessful;
        loginFailed = repo.loginFailed;
    }

    public void checkDataValidation(String email, String pass) {
        this.email = email;
        this.pass = pass;
        Boolean isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                pass.length() >= 6;
        isDataValid.setValue(isValid);
    }

    public void login() {
        repo.login(email, pass);
    }

}
