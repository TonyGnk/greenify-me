package com.example.greenifyme.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.navigation.Navigation;

import com.example.greenifyme.R;
import com.example.greenifyme.data.account.HashPasswordKt;
import com.example.greenifyme.ui.user.UserHomeActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class PasswordFragment extends Fragment {

    private PasswordViewModel mViewModel;
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate layout
        View view = inflater.inflate(R.layout.fragment_password, container, false);

        //Getting components
        Button buttonForgotPassword = view.findViewById(R.id.forgotPasswordButton);
        TextView emailPreviewText = view.findViewById(R.id.emailPreviewText);
        TextInputEditText txtPassword = view.findViewById(R.id.passwordEditTextLogin);
        Button buttonSignInLogin = view.findViewById(R.id.buttonSignInLogin);

        //Getting email from Login email
        SharedPreferences sp = requireContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        //will be used as a parameter to authenticate account
        String email = sp.getString("email", "");
        emailPreviewText.setText(email);

        //submit button functionality
        buttonSignInLogin.setOnClickListener(v -> {
            setPassword(Objects.requireNonNull(txtPassword.getText()).toString()); //Storing email to pass to isEmailRegistered()
            //String hashedPassword = HashPasswordKt.hashPassword(password); // i don't need to hash the password apparently?

            //Checks if passwordField is not null and if it matches with the email in the DataBase
            if (!TextUtils.isEmpty(getPassword()) &&  mViewModel.isCredentialsCorrect(email, getPassword())) {
                //TODO: Need to hide login fragments after this redirect
                Intent intent = new Intent(view.getContext(), UserHomeActivity.class);
                view.getContext().startActivity(intent);
            } else {
                txtPassword.setError("The password is not correct!, Try again!"); //Not doing anything :)
            }
        });

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