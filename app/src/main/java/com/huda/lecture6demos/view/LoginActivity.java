package com.huda.lecture6demos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.huda.lecture6demos.viewmodel.LoginViewModel;
import com.huda.lecture6demos.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser != null) {
            goToHomeActivity();
        } else {
            loginAuth();
        }
    }

    private void loginAuth() {
        binding.login.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String pass = binding.pass.getText().toString();
            viewModel.checkDataValidation(email, pass);
        });
        viewModel.isDataValid.observe(this, isValid -> {
            if (isValid) {
                viewModel.login();
            } else {
                Toast.makeText(this,
                        "Email or password not correct",
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.loginSuccessful.observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            goToHomeActivity();
        });
        viewModel.loginFailed.observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });
        binding.signUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}