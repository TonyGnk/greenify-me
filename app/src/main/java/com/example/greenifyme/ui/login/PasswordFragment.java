package com.example.greenifyme.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenifyme.R;
import com.google.android.material.textfield.TextInputEditText;

public class PasswordFragment extends Fragment {

    private LoginModel model;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        TextInputEditText txtPassword = view.findViewById(R.id.passwordEditTextLogin);
        Button buttonSignInLogin = view.findViewById(R.id.buttonSignInLogin);
        TextView emailPreviewText = view.findViewById(R.id.emailPreviewText);
        Button buttonForgotPassword = view.findViewById(R.id.forgotPasswordButton);


        model = new ViewModelProvider(requireActivity()).get(LoginModel.class);
        model.resetLoginState();

        buttonSignInLogin.setOnClickListener(v -> {
            String password = txtPassword.getText() != null ? txtPassword.getText().toString() : "";
            model.updatePassword(password);
            model.onSignInPressed();
        });

        buttonForgotPassword.setOnClickListener(v -> Toast.makeText(getContext(), "Forgot Password Not implemented!", Toast.LENGTH_LONG).show());

        observeViewModel(view, txtPassword, emailPreviewText);
        return view;
    }

    private void observeViewModel(View view, TextInputEditText txtPassword, TextView emailPreviewText) {
        model.getPasswordState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState != null) {
                emailPreviewText.setText(uiState.getEmail());
                if (uiState.isSignedIn()) {
                    model.navigateToUserHome(view);
                } else if (uiState.isPasswordError()) {
                    txtPassword.setError("The password is not correct! Try again!");
                }
            }
        });
    }
}