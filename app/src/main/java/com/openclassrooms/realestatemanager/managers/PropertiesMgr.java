package com.openclassrooms.realestatemanager.managers;

import com.openclassrooms.realestatemanager.models.Property;
import java.util.ArrayList;
import java.util.List;

public class PropertiesMgr {
    private static final PropertiesMgr ourInstance = new PropertiesMgr();

    public static PropertiesMgr getInstance() {
        return ourInstance;
    }

    private PropertiesMgr() {
    }

    public List<Property> getRealEstateProperties(){
        List<Property> properties = new ArrayList<>();

        //Get List of properties from Database

        return properties;
    }
}
