package com.huda.lecture6demos.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseRepo {

    public MutableLiveData<String> signUpSuccessful = new MutableLiveData<>();
    public MutableLiveData<String> signUpFailed = new MutableLiveData<>();
    public MutableLiveData<String> loginSuccessful = new MutableLiveData<>();
    public MutableLiveData<String> loginFailed = new MutableLiveData<>();
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void signUp(String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signUpSuccessful.setValue("Sign up was Successful");
                    } else {
                        signUpFailed.setValue("Sign up Failed");
                    }
                });
    }

    public void logout() {
        auth.signOut();
    }

    public void login(String email, String pass) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        loginSuccessful.setValue("Login was Successful");
                    } else {

                        loginFailed.setValue(task.getException().getMessage());
                    }
                });

    }
}
