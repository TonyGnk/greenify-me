package com.example.greenifyme.ui.login;

import static com.example.greenifyme.ui.login.RegisterNewModelKt.setEmailField;

import android.os.Bundle;
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
    //SharedPreferences sp;
    private RegisterViewModel model;

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
        View view = inflater.inflate(R.layout.fragment_login, container,
                false
        );

        //Get all components
        Button buttonNextLogin = view.findViewById(R.id.buttonNextLogin);
        Button buttonCreateAccount =
                view.findViewById(R.id.buttonCreateAccount);
        TextInputEditText txtEmailAddress =
                view.findViewById(R.id.emailEditTextLogin);
        TextInputLayout txtEmailLayout =
                view.findViewById(R.id.emailInputLayout);

        //		sp = requireContext().getSharedPreferences(
        //				"myUserPrefs",
        //				Context.MODE_PRIVATE
        //		);
        // Set the onClickListeners - Welcome to the madness!

        //submit button functionality
        buttonNextLogin.setOnClickListener(v -> {
            model.sendEmailFieldToModel(
                    Objects.requireNonNull(txtEmailAddress.getText()).toString()
            );
            //LoginResult result = model.onLoginPressed();
            model.onLoginPressed(result -> {
                        switch (Objects.requireNonNull(result)) {
                            case EMAIL_NOT_VALID_OR_EMPTY:
                                txtEmailLayout.setErrorEnabled(true);
                                txtEmailLayout.setError(
                                        "Not a valid email " +
                                                "address. Try " +
                                                "again or " +
                                                "Register!");
                                break;
                            case EMAIL_NOT_REGISTERED:
                                txtEmailLayout.setErrorEnabled(true);
                                txtEmailLayout.setError(
                                        "Email not registered. " +
                                                "Try again or " +
                                                "Register!");
                                break;
                            case SUCCESS:
                                //SharedPreferences.Editor editor
                                // = sp.edit();
                                //editor.putString("email",
                                // getEmail());
                                //editor.apply();
                                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_passwordFragment);
                                break;
                        }
                    }
            );

            //setEmail(Objects.requireNonNull(txtEmail.getText()).toString());
            //Storing email to pass to isEmailRegistered()

            //Checks if email is not null, valid format and if it exists in
            // the DB
            //if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(
            //		email).matches() && model.isEmailRegistered(getEmail())) {
            //TODO: Find a way to pass email to passwordFragment ->
            // TextView & Authentication
            //SharedPreferences.Editor editor = sp.edit();
            //editor.putString("email", getEmail());
            //editor.apply();
            //Navigation.findNavController(view).navigate(R.id
            // .action_loginFragment_to_passwordFragment); // Navigates to
            // passwordFragment
            //} else {
            //	txtEmailLayout.setErrorEnabled(true); //Not doing anything :)
            //	txtEmail.setError(
            //			"Not a valid email address. Try again or Register!");
            //	}
        });

        //Navigates to RegisterFragment
        buttonCreateAccount.setOnClickListener(v -> {
            setEmailField(Objects.requireNonNull(txtEmailAddress.getText()).toString());
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
        });
        return view;
    }
}