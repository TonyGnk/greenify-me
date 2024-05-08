package com.example.greenifyme.ui.admin;


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
