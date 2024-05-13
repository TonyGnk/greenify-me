package com.example.greenifyme.ui.login;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.greenifyme.R;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //Inflate layout
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        // Get all the components
        Button buttonNextLogin = view.findViewById(R.id.buttonNextLogin);
        Button buttonCreateAccount = view.findViewById(R.id.buttonCreateAccount);


        // Set the onClickListeners
        buttonNextLogin.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_passwordFragment));
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