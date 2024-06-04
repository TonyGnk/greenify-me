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
import androidx.navigation.fragment.NavHostFragment;

import com.example.greenifyme.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private LoginModel model;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button buttonNextLogin = view.findViewById(R.id.buttonNextLogin);
        Button buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        TextInputEditText txtEmailAddress = view.findViewById(R.id.emailEditTextLogin);
        TextInputLayout txtEmailLayout = view.findViewById(R.id.emailInputLayout);

        model = new ViewModelProvider(requireActivity()).get(LoginModel.class);
        buttonNextLogin.setOnClickListener(v -> {
            String email = txtEmailAddress.getText() != null ? txtEmailAddress.getText().toString() : "";
            model.updateEmail(email);
            model.onNextPressed();
        });

        buttonCreateAccount.setOnClickListener(v -> {
            String email = txtEmailAddress.getText() != null ? txtEmailAddress.getText().toString() : "";
            model.updateEmail(email);
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_registerFragment);
        });


        observeViewModel(view, txtEmailLayout);
        return view;
    }

    private void observeViewModel(View view, TextInputLayout txtEmailLayout) {
        model.getLoginState().observe(getViewLifecycleOwner(), uiState -> {
            if (uiState != null && uiState.getType() != null) {
                switch (Objects.requireNonNull(uiState.getType())) {
                    case NOT_REGISTERED:
                        txtEmailLayout.setError("Email not registered. Try again or Register!");
                        break;
                    case EMPTY:
                        txtEmailLayout.setError("Email field is empty. Try again or Register!");
                        break;
                    case WRONG_FORMAT:
                        txtEmailLayout.setError("Not a valid email address. Try again or Register!");
                        break;
                    case SUCCESS:
                        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_passwordFragment);
                        break;
                }
            }
        });
    }
}