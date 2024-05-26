package com.example.kindastoreapi.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.kindastoreapi.models.ModelM;
import com.example.kindastoreapi.repositories.JamRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private JamRepository jemRepository;
    private LiveData<List<ModelM>> modelMResponseLiveData;
    public HomeViewModel() {
        jemRepository = new JamRepository();
        modelMResponseLiveData = jemRepository.getDashJeminyList();
    }

    public LiveData<List<ModelM>> getModelMResponseLiveData() {
        return modelMResponseLiveData;
    }
}