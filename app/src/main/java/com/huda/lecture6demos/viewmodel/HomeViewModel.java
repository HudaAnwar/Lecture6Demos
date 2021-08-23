package com.huda.lecture6demos.viewmodel;

import androidx.lifecycle.ViewModel;

import com.huda.lecture6demos.repo.FirebaseRepo;

public class HomeViewModel extends ViewModel {
    private FirebaseRepo repo = new FirebaseRepo();

    public void logout() {
        repo.logout();
    }
}
