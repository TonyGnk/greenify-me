package com.example.greenifyme.ui.admin;

import android.app.Application;
import android.widget.TextView;
import android.os.Handler;
import android.icu.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import androidx.lifecycle.ViewModel;

public class AdminHomeViewModel extends ViewModel {
    private String greetingText;

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

}
