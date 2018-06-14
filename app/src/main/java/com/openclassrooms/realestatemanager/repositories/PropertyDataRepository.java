package com.openclassrooms.realestatemanager.repositories;

import android.arch.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.database.dao.PropertyDAO;
import com.openclassrooms.realestatemanager.models.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyDataRepository {

    private final PropertyDAO propertyDAO;

    public PropertyDataRepository(PropertyDAO propertyDAO){
        this.propertyDAO = propertyDAO;
    }

    // CREATE
    public void createProperty(Property property){
        propertyDAO.insertProperty(property);
    }

    // READ
    public LiveData<List<Property>> getAllProperties(){
        return this.propertyDAO.getAllProperties();
    }

    public LiveData<List<Property>> getSearchedProperties(String area, long priceMin, long priceMax,
                                                          int surfaceMin, int surfaceMax, int nbRooms,
                                                          int nbPhotos, boolean isSold, int date, String [] propertyTypes){
        return this.propertyDAO.getSearchedProperties(area, priceMin, priceMax, surfaceMin, surfaceMax,
                nbRooms, nbPhotos, isSold, date, propertyTypes);
    }


    public LiveData<Property> getProperty(long propertyId){
        return this.propertyDAO.getProperty(propertyId);
    }

    // UPDATE
    public void updateProperty(Property property){
        propertyDAO.updateProperty(property);
    }

}
