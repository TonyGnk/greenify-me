package com.example.greenifyme.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.greenifyme.ApplicationSetup;
import com.example.greenifyme.data.GreenRepository;

public class LoginViewModel extends AndroidViewModel {
    private final GreenRepository mRepository;

    public LoginViewModel(Application application) {
        super(application);
        ApplicationSetup app = (ApplicationSetup) application;
        mRepository = app.greenRepository;

    }

    public boolean isEmailRegistered(String email) {
        return mRepository.accountExists(email);
    }
}