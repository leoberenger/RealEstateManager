package com.openclassrooms.realestatemanager.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity (foreignKeys = @ForeignKey(entity = Property.class,
        parentColumns = "id",
        childColumns = "propertyId"))

public class Adress {

    private long propertyId;
    private String streetNb;
    private String streetName;
    private String apptNb;
    private String zipCode;
    private String stateNb;
    private String city;
    private String country;

    //---------------------------
    //CONSTRUCTOR
    //--------------------------

    public Adress(long propertyId, String streetNb, String streetName, String apptNb, String zipCode, String stateNb, String city, String country) {
        this.propertyId = propertyId;
        this.streetNb = streetNb;
        this.streetName = streetName;
        this.apptNb = apptNb;
        this.zipCode = zipCode;
        this.stateNb = stateNb;
        this.city = city;
        this.country = country;
    }


    //---------------------------
    //GETTERS
    //--------------------------

    public long getPropertyId() {
        return propertyId;
    }

    public String getStreetNb() {
        return streetNb;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getApptNb() {
        return apptNb;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getStateNb() {
        return stateNb;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }


    //---------------------------
    //SETTERS
    //--------------------------

    public void setStreetNb(String streetNb) {
        this.streetNb = streetNb;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public void setApptNb(String apptNb) {
        this.apptNb = apptNb;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public void setStateNb(String stateNb) {
        this.stateNb = stateNb;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }

}

