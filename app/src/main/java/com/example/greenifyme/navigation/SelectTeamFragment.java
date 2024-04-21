package com.example.greenifyme.navigation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.greenifyme.R;

public class SelectTeamFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Always get the view first
        View view = inflater.inflate(R.layout.select_team_screen, container, false);


        // Get all the components
        Button buttonDatabase = view.findViewById(R.id.buttonDatabase);
        Button buttonUser = view.findViewById(R.id.buttonUser);
        Button buttonAdmin = view.findViewById(R.id.buttonAdmin);
        Button buttonTesting = view.findViewById(R.id.buttonTesting);

        // Set the onClickListeners
        buttonDatabase.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_loginFragment));
        buttonUser.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_userHomeFragment));
        buttonAdmin.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_adminHomeFragment));
        buttonTesting.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_firstFragment));


        return view;

    }

}