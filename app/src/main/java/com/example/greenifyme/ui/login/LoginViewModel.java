package com.example.greenifyme.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.greenifyme.ApplicationSetup;
import com.example.greenifyme.data.GreenRepository;

public class LoginViewModel extends AndroidViewModel {
    private GreenRepository mRepository;

    public LoginViewModel(Application application) {
        super(application);
        ApplicationSetup app = (ApplicationSetup) application;
        mRepository = app.greenRepository;
    }
}