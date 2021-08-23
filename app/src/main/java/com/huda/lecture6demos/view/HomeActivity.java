package com.huda.lecture6demos.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.huda.lecture6demos.databinding.ActivityHomeBinding;
import com.huda.lecture6demos.viewmodel.HomeViewModel;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getSavedEmail();
        viewModel.emailLiveData.observe(this, s -> {
            binding.email.setText(s + "\n you are logged in");
        });
        binding.logout.setOnClickListener(v -> {
            viewModel.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
        viewModel.updateToken();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        viewModel.sendNotification(uid, "You just logged in");

    }
}