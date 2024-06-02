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
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private LoginModel model;

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

        TextInputLayout txtNameLayout = view.findViewById(R.id.nameRegTextLayout);
        TextInputLayout txtEmailLayout = view.findViewById(R.id.emailRegTextLayout);
        TextInputLayout txtPasswordLayout = view.findViewById(R.id.passRegText);
        TextInputLayout txtPassConfirmLayout = view.findViewById(R.id.passConfRegTextLayout);

        model = new ViewModelProvider(requireActivity()).get(LoginModel.class);

        buttonRegisterSubmit.setOnClickListener(v -> {
            model.updateRegisterState(
                    Objects.requireNonNull(txtName.getText()).toString(),
                    Objects.requireNonNull(txtEmailAddress.getText()).toString(),
                    Objects.requireNonNull(txtPassword.getText()).toString(),
                    Objects.requireNonNull(txtConfirmPassword.getText()).toString()
            );
            model.onRegisterPressed(view);
        });
        observeViewModel(txtEmailAddress, txtEmailLayout, txtNameLayout, txtPasswordLayout, txtPassConfirmLayout);
        return view;
    }

    private void observeViewModel(
            TextInputEditText txtEmailAddress, TextInputLayout txtEmailLayout, TextInputLayout txtNameLayout, TextInputLayout txtPasswordLayout, TextInputLayout txtPassConfirmLayout
    ) {
        model.getRegisterState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState != null) {
                txtEmailAddress.setText(uiState.getEmail());
                RegisterResult type = Objects.requireNonNull(uiState.getType());
                if (type == RegisterResult.EMAIL_NOT_VALID_OR_EMPTY) {
                    txtEmailLayout.setError("The field cannot be empty, or not valid email");
                } else if (type == RegisterResult.EMAIL_EXIST) {
                    txtEmailLayout.setError("There is already an account with this email");
                } else if (type == RegisterResult.NAME_EMPTY) {
                    txtNameLayout.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORD_EMPTY) {
                    txtPasswordLayout.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORD_CONFIRM_EMPTY) {
                    txtPassConfirmLayout.setError("Field cannot be empty");
                } else if (type == RegisterResult.PASSWORDS_NOT_MATCHING) {
                    txtPassConfirmLayout.setError("Password's are not matching");
                }
            }
        });
    }
}