package com.example.greenifyme.navigation;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.greenifyme.R;
import com.example.greenifyme.data.database_example.NewWordActivity;
import com.example.greenifyme.ui.database_manager.DBManagerActivity;

public class SelectTeamFragment extends Fragment {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Always get the view first
        View view = inflater.inflate(R.layout.select_team_screen, container, false);


        // Get all the components
        Button buttonDatabase = view.findViewById(R.id.buttonDatabase);
        Button buttonUser = view.findViewById(R.id.buttonUser);
        Button buttonAdminCompose = view.findViewById(R.id.buttonAdminCompose);
        Button buttonDatabaseManager = view.findViewById(R.id.buttonDatabaseManager);

        // Set the onClickListeners
        buttonDatabase.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_loginFragment));
        buttonUser.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_userHomeFragment));
        buttonAdminCompose.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_select_team_screen_to_quantityFragment));

        buttonDatabaseManager.setOnClickListener(
                v -> {
                    Intent intent = new Intent(view.getContext(), DBManagerActivity.class);
                    startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                }
        );

        return view;

    }

}