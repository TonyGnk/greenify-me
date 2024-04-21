package com.example.greenifyme.ui.database_example;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.greenifyme.data.database_example.Word;
import com.example.greenifyme.data.database_example.WordRepository;

import java.util.List;

public class FirstViewModel extends AndroidViewModel {
    private final LiveData<List<Word>> mAllWords;
    private final WordRepository mRepository;

    public FirstViewModel(Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}