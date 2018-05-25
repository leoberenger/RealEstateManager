package com.openclassrooms.realestatemanager.managers;

import com.openclassrooms.realestatemanager.models.Property;
import java.util.ArrayList;
import java.util.Calendar;
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

        Property property1 = new Property(1, "Leclerc", 48.393379, -4.483767);
        Property property2 = new Property(2, "Hôpital", 48.393364, -4.483760);
        Property property3 = new Property(3, "Chevaline", 48.393251, -4.483763);
        Property property4 = new Property(4, "Boulangerie", 48.393460, -4.483792);
        Property property5 = new Property(5, "Ecole", 48.393675, -4.483780);
        Property property6 = new Property(6, "Hotel", 48.393582, -4.483750);

        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        properties.add(property5);
        properties.add(property6);



        return properties;
    }
}
