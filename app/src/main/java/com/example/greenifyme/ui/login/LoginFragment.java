package com.example.greenifyme.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.greenifyme.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;

public class LoginFragment extends Fragment {
   SharedPreferences sp;
    private LoginViewModel mViewModel;
    private String email; //Variable to store email address from textInput
    public String getEmail() {
        return email;
    }
    public void setEmail(String name) {
        this.email = name;
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate layout
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        //Get all components
        Button buttonNextLogin = view.findViewById(R.id.buttonNextLogin);
        Button buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        TextInputEditText txtEmail = view.findViewById(R.id.emailEditTextLogin);
        TextInputLayout txtEmailLayout = view.findViewById(R.id.emailInputLayout);

        sp = requireContext().getSharedPreferences("myUserPrefs", Context.MODE_PRIVATE);
        // Set the onClickListeners - Welcome to the madness!

        //submit button functionality
        buttonNextLogin.setOnClickListener(v -> {

            setEmail(Objects.requireNonNull(txtEmail.getText()).toString()); //Storing email to pass to isEmailRegistered()

            //Checks if email is not null, valid format and if it exist in the DB
            if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && mViewModel.isEmailRegistered(getEmail())) {
                //TODO: Find a way to pass email to passwordFragment -> TextView & Authentication
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", getEmail());
                editor.apply();
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_passwordFragment); // Navigates to passwordFragment
            } else {
                txtEmailLayout.setErrorEnabled(true); //Not doing anything :)
                txtEmail.setError("Not a valid email address. Try again or Register!");
            }

        });

        //Navigates to RegisterFragment
        buttonCreateAccount.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

}