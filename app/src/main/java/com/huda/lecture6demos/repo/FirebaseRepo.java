package com.huda.lecture6demos.repo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                        saveEmail(email);
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

    private void saveEmail(String email) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return;
        }
        String uid = firebaseUser.getUid();
        FirebaseDatabase.getInstance().getReference("user")
                .child(uid).setValue(email);
    }

    public MutableLiveData<String> getSavedEmail() {
        MutableLiveData<String> emailLiveData = new MutableLiveData<>();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            return emailLiveData;
        }
        String uid = firebaseUser.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user")
                .child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    emailLiveData.setValue(snapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return emailLiveData;
    }
}
