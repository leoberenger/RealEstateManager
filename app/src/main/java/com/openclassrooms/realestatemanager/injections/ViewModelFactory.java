package com.openclassrooms.realestatemanager.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.openclassrooms.realestatemanager.repositories.PropertyDataRepository;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory{

    private final PropertyDataRepository propertyDataSource;
    private final Executor executor;

    public ViewModelFactory(PropertyDataRepository propertyDataSource, Executor executor){
        this.propertyDataSource = propertyDataSource;
        this.executor = executor;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PropertyViewModel.class)){
            return (T) new PropertyViewModel(propertyDataSource, executor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
