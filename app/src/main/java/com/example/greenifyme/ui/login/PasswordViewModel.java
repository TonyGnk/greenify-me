package com.example.greenifyme.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.greenifyme.ApplicationSetup;
import com.example.greenifyme.data.GreenRepository;

public class PasswordViewModel extends AndroidViewModel {
    private final GreenRepository mRepository;

    public PasswordViewModel(Application application) {
        super(application);
        ApplicationSetup app = (ApplicationSetup) application;
        mRepository = app.greenRepository;

    }
    public boolean isCredentialsCorrect(String email, String hash) {
        return mRepository.accountExists(email, hash);
    }

}