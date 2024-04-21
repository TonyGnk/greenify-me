package com.example.greenifyme.ui.database_example;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.greenifyme.R;
import com.example.greenifyme.data.database_example.NewWordActivity;
import com.example.greenifyme.data.database_example.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class FirstFragment extends Fragment {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private FirstViewModel mViewModel;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        final WordListAdapter adapter = new WordListAdapter(new WordListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);

        // Update the cached copy of the words in the adapter.
        mViewModel.getAllWords().observe(getViewLifecycleOwner(), adapter::submitList);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(currentView -> {
            Intent intent = new Intent(view.getContext(), NewWordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            //registerForActivityResult()
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FirstViewModel.class);
        // TODO: Use the ViewModel

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(Objects.requireNonNull(data.getStringExtra(NewWordActivity.EXTRA_REPLY)));
            mViewModel.insert(word);
        } else {
            Toast.makeText(
                    getContext(),
                    "empty_not_saved",
                    Toast.LENGTH_LONG).show();
        }
    }

}

