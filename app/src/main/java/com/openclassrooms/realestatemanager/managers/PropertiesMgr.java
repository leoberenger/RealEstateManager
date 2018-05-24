package com.openclassrooms.realestatemanager.managers;

import com.openclassrooms.realestatemanager.models.RealEstate;

import java.util.ArrayList;
import java.util.List;

public class PropertiesMgr {
    private static final PropertiesMgr ourInstance = new PropertiesMgr();

    public static PropertiesMgr getInstance() {
        return ourInstance;
    }

    private PropertiesMgr() {
    }

    public List<RealEstate.Property> getRealEstateProperties(){
        List<RealEstate.Property> properties = new ArrayList<>();

        //Get List of properties from Database

        return properties;
    }
}
