package com.huda.lecture6demos.viewmodel;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.huda.lecture6demos.repo.FirebaseRepo;

public class HomeViewModel extends ViewModel {
    private FirebaseRepo repo = new FirebaseRepo();
    public MutableLiveData<String> emailLiveData = new MutableLiveData<>();

    public void logout() {
        repo.logout();
    }

    public void getSavedEmail() {
        emailLiveData = repo.getSavedEmail();
    }
    public void updateToken() {
        repo.updateToken();
    }
    public void sendNotification(String id,String msg){
        repo.getNotificationData(id,msg);
    }
}
