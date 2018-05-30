package com.openclassrooms.realestatemanager.injections;

import android.content.Context;

import com.openclassrooms.realestatemanager.database.dao.RealEstateManagerDatabase;
import com.openclassrooms.realestatemanager.repositories.PropertyDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {
    public static PropertyDataRepository providePropertyDataSource(Context context) {
        RealEstateManagerDatabase database = RealEstateManagerDatabase.getInstance(context);
        return new PropertyDataRepository(database.propertyDao());
    }


    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        PropertyDataRepository dataSourceProperty = providePropertyDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceProperty, executor);
    }
}
