package com.openclassrooms.realestatemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    private String streetNb;
    private String streetName;
    private String apptNb;
    private String zipCode;
    private String stateNb;
    private String city;
    private String country;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Address(){}

    public Address(String streetNb, String streetName, String apptNb, String zipCode, String stateNb, String city, String country) {
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

    //---------------------------
    //PARCELABLE METHODS
    //--------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(streetName);
        out.writeString(apptNb);
        out.writeString(zipCode);
        out.writeString(stateNb);
        out.writeString(city);
        out.writeString(country);
    }

    public static final Parcelable.Creator<Address> CREATOR
            = new Parcelable.Creator<Address>() {

        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    private Address(Parcel in) {
        streetNb = in.readString();
        streetName = in.readString();
        apptNb = in.readString();
        zipCode = in.readString();
        stateNb = in.readString();
        city = in.readString();
        country = in.readString();
    }
}
