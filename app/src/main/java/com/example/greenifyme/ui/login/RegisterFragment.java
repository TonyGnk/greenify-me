package com.example.greenifyme.ui.login;

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

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private String name;
    private String email;
    private String password;
    private String passwordConfirm;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate layout
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        //Get components
        TextInputEditText txtName = view.findViewById(R.id.nameRegTextInput);
        TextInputEditText txtEmailAddress = view.findViewById(R.id.emailRegTextInput);
        TextInputEditText txtPassword = view.findViewById(R.id.passRegTextInput);
        TextInputEditText txtConfirmPassword = view.findViewById(R.id.passConfRegTextInput);
        Button buttonRegisterSubmit = view.findViewById(R.id.buttonRegisterSubmit);

        //submit button functionality
        buttonRegisterSubmit.setOnClickListener(v -> {

            setName(Objects.requireNonNull(txtName.getText()).toString()); //Storing email to pass to isEmailRegistered()
            setEmail(Objects.requireNonNull(txtEmailAddress.getText()).toString());
            setPassword(Objects.requireNonNull(txtPassword.getText()).toString());
            setPasswordConfirm(Objects.requireNonNull(txtConfirmPassword.getText()).toString());

            //Checks if email is not null, valid format and if it exist in the DB
            if (!TextUtils.isEmpty(getEmail()) && Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                    !TextUtils.isEmpty(getName()) && !TextUtils.isEmpty(getPassword()) && !TextUtils.isEmpty(getPasswordConfirm())
                    && txtPassword.equals(txtConfirmPassword)) {


                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_adminHomeFragment);
            } else {
                if (TextUtils.isEmpty(getEmail()) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtEmailAddress.setError("The field cannot be empty, or not valid email");
                } else if (TextUtils.isEmpty(getName())) {
                    txtName.setError("Field cannot be empty");
                } else if (TextUtils.isEmpty(getPassword())) {
                    txtPassword.setError("Field cannot be empty");
                } else if (TextUtils.isEmpty(getPasswordConfirm())) {
                    txtConfirmPassword.setError("Field cannot be empty");
                } else {
                    txtConfirmPassword.setError("Password's are not matching");
                }
            }

        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

}