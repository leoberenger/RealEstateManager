package com.openclassrooms.realestatemanager.database;

import com.openclassrooms.realestatemanager.models.Address;
import com.openclassrooms.realestatemanager.models.Property;

public class DataGenerator {

    public static Property [] generateProperties(){
    Property[] properties = new Property[3];
        for (int i = 0; i<properties.length; i++){
        properties[i] = new Property();
    }

    Address address0 = new Address(
            "4", "Glasgow", "4", "29200", "Brest", "France");
    Address address1 = new Address(
            "1", "Danton", "1", "29200", "Brest", "France");
    Address address2 = new Address(
            "2", "Jaurès", "0", "29200", "Brest", "France");

    properties[0] = new Property(
            "Port", 48.392151, -4.479032,
            95000, 125, 7,
            "Très bel appartement en bord de mer, proche de tous commerces",
            "https://picsum.photos/200/200/?image=11", "Garden", 1,
            0, 20180201, 0, Property.typesNames[1], 3,
            1, 1, 0, 0,
            address0);

    properties[1] = new Property("Centre ville",48.380143,-4.487213,
            250000,215,11,
            "Au centre ville, maison de charme pour grande famille avec jardin",
            "https://picsum.photos/200/200/?image=122", "Tower", 1,
            0,20180401,0, Property.typesNames[0], 1,
            0, 0, 1, 0,
            address1);

    properties[2] = new Property( "Centre ville", 48.406232, -4.496259,
            328000, 120, 4,
            "Immense loft au coeur du centre ville, parfait pour créateurs de startup",
            "https://picsum.photos/200/200/?image=10", "View", 1,
            1,20180131,20180401, Property.typesNames[3], 2,
            1, 1, 0, 1,
            address2);

    return properties;
    }
}
