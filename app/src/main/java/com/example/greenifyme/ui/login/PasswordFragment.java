package com.example.greenifyme.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.greenifyme.R;

public class PasswordFragment extends Fragment {

    private PasswordViewModel mViewModel;

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        Button buttonForgotPassword = view.findViewById(R.id.forgotPasswordButton);
        buttonForgotPassword.setOnClickListener(v -> Toast.makeText(getContext(), "Forgot Password Not implemented!", Toast.LENGTH_LONG).show());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PasswordViewModel.class);
        // TODO: Use the ViewModel
    }

}