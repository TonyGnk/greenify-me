package com.example.greenifyme.ui.admin;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.greenifyme.R;

import java.util.Calendar;

public class AdminHomeFragment extends Fragment {

    private AdminHomeViewModel viewModel; 
    private TextView greetingTextView;

    public static AdminHomeFragment newInstance() {
        return new AdminHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        greetingTextView = root.findViewById(R.id.greeting_txt);
        viewModel = new ViewModelProvider(this).get(AdminHomeViewModel.class);
        updateGreetingText();
        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    private void updateGreetingText() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        if (hourOfDay >= 6 && hourOfDay < 12) {
            greeting = "Good Morning";
        } else {
            greeting = "Good Evening";
        }

        if (viewModel != null) {
            viewModel.setGreetingText(greeting);
            greetingTextView.setText(viewModel.getGreetingText());
        } else {
            Log.e("AdminHomeFragment", "ViewModel not initialized!");
        }
    }
}