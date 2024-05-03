package com.example.greenifyme.ui.admin;

import androidx.lifecycle.ViewModelProvider;

import android.app.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStoreOwner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greenifyme.R;

import java.util.Calendar;

public class AdminHomeFragment extends Fragment {

    private AdminHomeViewModel viewModel;
    private TextView greetingTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_admin_home, container, false);
        greetingTextView = root.findViewById(R.id.greeting_txt);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(AdminHomeViewModel.class);
        updateGreetingText();
    }

    private void updateGreetingText() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        if (hourOfDay >= 6 && hourOfDay < 12) {
            greeting = "Καλημέρα";
        } else {
            greeting = "Καλησπέρα";
        }

        viewModel.setGreetingText(greeting);
        greetingTextView.setText(viewModel.getGreetingText());
    }

    public static AdminHomeFragment newInstance() {
        return new AdminHomeFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}