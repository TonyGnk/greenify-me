package com.example.greenifyme.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.greenifyme.ApplicationSetup;
import com.example.greenifyme.data.GreenRepository;

public class RegisterViewModel extends AndroidViewModel {
    private final GreenRepository mRepository;

    public RegisterViewModel(Application application) {
        super(application);
        ApplicationSetup app = (ApplicationSetup) application;
        mRepository = app.greenRepository;

    }
    public boolean registerToDB(String name, String email, String password) {
        return mRepository.accountExists(email, password);
    }
}