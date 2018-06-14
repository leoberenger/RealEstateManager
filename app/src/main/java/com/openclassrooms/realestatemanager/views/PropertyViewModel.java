package com.openclassrooms.realestatemanager.views;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.repositories.PropertyDataRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class PropertyViewModel extends ViewModel {

    //REPOSITORIES
    private final PropertyDataRepository propertyDataSource;
    private final Executor executor;

    //DATA
    @Nullable
    private LiveData<List<Property>> properties;

    public PropertyViewModel(PropertyDataRepository propertyDataSource, Executor executor){
        this.propertyDataSource = propertyDataSource;
        this.executor = executor;
    }

    public void init(){
        if(this.properties != null){return;}
            properties = propertyDataSource.getAllProperties();
    }

    //------------------------
    // FOR PROPERTY
    //------------------------

    public LiveData<List<Property>> getAllProperties(){
        return propertyDataSource.getAllProperties();
    }

    public LiveData<List<Property>> getSearchedProperties(String area, long priceMin, long priceMax,
                                                          int surfaceMin, int surfaceMax, int nbRooms,
                                                          int nbPhotos, boolean isSold, int date, String [] propertyTypes){
        return propertyDataSource.getSearchedProperties(area, priceMin, priceMax, surfaceMin, surfaceMax,
                nbRooms, nbPhotos, isSold, date, propertyTypes);
    }

    public LiveData<Property> getProperty(long propertyId){
        return propertyDataSource.getProperty(propertyId);
    }

    public void createProperty (Property property){
        executor.execute(()->{
            propertyDataSource.createProperty(property);
        });
    }

    public void updateProperty (Property property){
        executor.execute(() -> {
            propertyDataSource.updateProperty(property);
        });
    }
}
