package com.huda.lecture6demos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.huda.lecture6demos.viewmodel.SignUpViewModel;
import com.huda.lecture6demos.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.signUp.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String pass = binding.pass.getText().toString();
            viewModel.checkDataValidation(email, pass);
        });
        viewModel.isDataValid.observe(this, isValid -> {
            if (isValid) {
                viewModel.signUp();
            } else {
                Toast.makeText(this,
                        "Email or password not correct",
                        Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.signUpSuccessful.observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            goToHomeActivity();
        });
        viewModel.signUpFailed.observe(this, s -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });
    }

    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}