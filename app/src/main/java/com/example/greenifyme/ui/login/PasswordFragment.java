package com.example.greenifyme.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.greenifyme.ui.user.UserHomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class PasswordFragment extends Fragment {

    private RegisterViewModel model;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        //Inflate layout
        View view = inflater.inflate(
                R.layout.fragment_password,
                container,
                false
        );

        //Getting components
        Button buttonForgotPassword =
                view.findViewById(R.id.forgotPasswordButton);
        TextView emailPreviewText = view.findViewById(R.id.emailPreviewText);
        TextInputEditText txtPassword =
                view.findViewById(R.id.passwordEditTextLogin);
        Button buttonSignInLogin = view.findViewById(R.id.buttonSignInLogin);

        //Getting email from Login email
        SharedPreferences sp = requireContext().getSharedPreferences(
                "myUserPrefs",
                Context.MODE_PRIVATE
        );
        //will be used as a parameter to authenticate account
        String email = sp.getString("email", "");
        emailPreviewText.setText(email);

        buttonSignInLogin.setOnClickListener(v -> {
            model.sendPasswordFieldToModel(
                    Objects.requireNonNull(txtPassword.getText()).toString()
            );
            model.sendEmailFieldToModel(email);

            model.onSignInPressed(result -> {
                if (Boolean.TRUE.equals(result)) {
                    Intent intent = new Intent(
                            view.getContext(),
                            UserHomeActivity.class
                    );
                    view.getContext().startActivity(intent);
                } else {
                    txtPassword.setError("The password is not correct!, Try " +
                            "again!");
                }
            });
        });

        buttonForgotPassword.setOnClickListener(v -> Toast.makeText(
                getContext(),
                "Forgot Password Not implemented!",
                Toast.LENGTH_LONG
        ).show());
        return view;
    }
}