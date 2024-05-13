package com.example.greenifyme.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.greenifyme.R;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Inflate layout
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Get all the components
        Button buttonNextLogin = view.findViewById(R.id.buttonNextLogin);
        Button buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);
        TextInputEditText txtEmail = view.findViewById(R.id.emailEditTextLogin);

        // Set the onClickListeners

        buttonNextLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                setName(txtEmail.getText().toString()); //Temp string to store content of email input
                //Checks if email format is valid (does not check for real email addresses) and not empty
                if(!TextUtils.isEmpty(name) && Patterns.EMAIL_ADDRESS.matcher(name).matches()){
                    Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_passwordFragment);;
                }
                else {
                    //Generic input error that is not annoying everytime you type a character. Could change to red color border until is accepted
                    txtEmail.setError("Not a valid email address");
                    //TODO: Add a search on DB to check if an email is Registered.
                    Toast.makeText(getContext(), "No account found. Try a different email or Register", Toast.LENGTH_LONG).show();
                }

            }
        });
        buttonCreateAccount.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));
        
        
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

}