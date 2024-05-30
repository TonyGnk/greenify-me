package com.example.greenifyme.ui.login;

import static com.example.greenifyme.ui.login.RegisterNewModelKt.getEmailField;

import android.content.Intent;
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
import com.example.greenifyme.ui.user.UserHomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private RegisterViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(
                R.layout.fragment_register,
                container,
                false
        );

        //Get components
        TextInputEditText txtName = view.findViewById(R.id.nameRegTextInput);
        TextInputEditText txtEmailAddress =
                view.findViewById(R.id.emailRegTextInput);
        TextInputEditText txtPassword =
                view.findViewById(R.id.passRegTextInput);
        TextInputEditText txtConfirmPassword =
                view.findViewById(R.id.passConfRegTextInput);
        Button buttonRegisterSubmit =
                view.findViewById(R.id.buttonRegisterSubmit);

        txtEmailAddress.setText(getEmailField());

        buttonRegisterSubmit.setOnClickListener(v -> {
            model.sendFieldsToModel(
                    Objects.requireNonNull(txtName.getText()).toString(),
                    Objects.requireNonNull(txtEmailAddress.getText()).toString(),
                    Objects.requireNonNull(txtPassword.getText()).toString(),
                    Objects.requireNonNull(txtConfirmPassword.getText()).toString()
            );
            model.onRegisterPressed(result -> {
                switch (Objects.requireNonNull(result)) {
                    case SUCCESS:
                        Intent intent = new Intent(
                                view.getContext(),
                                UserHomeActivity.class
                        );
                        model.setNavigationAfter3Seconds(view);
                        view.getContext().startActivity(intent);
                        break;
                    case EMAIL_NOT_VALID_OR_EMPTY:
                        txtEmailAddress.setError(
                                "The field cannot be empty, or not valid email"
                        );
                        break;
                    case EMAIL_EXIST:
                        txtEmailAddress.setError(
                                "There is already an account with this email"
                        );
                        break;
                    case NAME_EMPTY:
                        txtName.setError("Field cannot be empty");
                        break;
                    case PASSWORD_EMPTY:
                        txtPassword.setError("Field cannot be empty");
                        break;
                    case PASSWORD_CONFIRM_EMPTY:
                        txtConfirmPassword.setError("Field cannot be empty");
                        break;
                    case PASSWORDS_NOT_MATCHING:
                        txtConfirmPassword.setError(
                                "Password's are not matching");
                        break;
                }
            });
        });

        return view;
    }
}