package com.huda.lecture6demos.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huda.lecture6demos.repo.FirebaseRepo;

public class SignUpViewModel extends ViewModel {

    public MutableLiveData<Boolean> isDataValid = new MutableLiveData<>();
    public MutableLiveData<String> signUpSuccessful;
    public MutableLiveData<String> signUpFailed;
    private String email;
    private String pass;
    private FirebaseRepo repo = new FirebaseRepo();


    public SignUpViewModel() {
        signUpSuccessful = repo.signUpSuccessful;
        signUpFailed = repo.signUpFailed;
    }

    public void checkDataValidation(String email, String pass) {
        this.email = email;
        this.pass = pass;
        Boolean isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                pass.length() >= 6;
        isDataValid.setValue(isValid);
    }

    public void signUp() {
        repo.signUp(email, pass);
    }

}
