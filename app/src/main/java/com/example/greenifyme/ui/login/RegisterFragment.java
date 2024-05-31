package com.example.greenifyme.ui.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.greenifyme.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private PasswordModel model;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //Get components
        TextInputEditText txtName = view.findViewById(R.id.nameRegTextInput);
        TextInputEditText txtEmailAddress = view.findViewById(R.id.emailRegTextInput);
        TextInputEditText txtPassword = view.findViewById(R.id.passRegTextInput);
        TextInputEditText txtConfirmPassword = view.findViewById(R.id.passConfRegTextInput);
        Button buttonRegisterSubmit = view.findViewById(R.id.buttonRegisterSubmit);

        model = new ViewModelProvider(requireActivity()).get(PasswordModel.class);

        buttonRegisterSubmit.setOnClickListener(v -> {
            model.updateRegisterState(
                    Objects.requireNonNull(txtName.getText()).toString(),
                    Objects.requireNonNull(txtEmailAddress.getText()).toString(),
                    Objects.requireNonNull(txtPassword.getText()).toString(),
                    Objects.requireNonNull(txtConfirmPassword.getText()).toString()
            );
            model.onRegisterPressed();
        });
        observeViewModel(view, txtEmailAddress, txtName, txtPassword, txtConfirmPassword);
        return view;
    }

    private void observeViewModel(
            View view, TextInputEditText txtEmailAddress, TextInputEditText txtName, TextInputEditText txtPassword, TextInputEditText txtConfirmPassword
    ) {
        model.getRegisterState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState != null) {
                txtEmailAddress.setText(uiState.getEmail());
                RegisterResult type = Objects.requireNonNull(uiState.getType());
                if (type == RegisterResult.EMAIL_NOT_VALID_OR_EMPTY) {
                    txtEmailAddress.setError("The field cannot be empty, or not valid email");
                } else if (type == RegisterResult.EMAIL_EXIST) {
                    txtEmailAddress.setError("There is already an account with this email");
                } else if (type == RegisterResult.NAME_EMPTY) {
                    txtName.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORD_EMPTY) {
                    txtPassword.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORD_CONFIRM_EMPTY) {
                    txtConfirmPassword.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORDS_NOT_MATCHING) {
                    txtConfirmPassword.setError("Password's are not matching");
                }
            }
        });
    }
}