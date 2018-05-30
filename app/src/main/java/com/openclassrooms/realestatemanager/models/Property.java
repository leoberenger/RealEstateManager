package com.openclassrooms.realestatemanager.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class Property{

    @PrimaryKey(autoGenerate = true) private long id;
    private String area;
    private Double latitude;
    private Double longitude;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Property() { }

    public Property(long id, String area, Double latitude, Double longitude){
        this.id = id;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getArea() {
        return area;
    }
    public Double getLatitude() {
        return latitude;
    }
    public Double getLongitude() {
        return longitude;
    }


    //---------------------------
    //SETTERS
    //--------------------------

    public void setId(long id) {
        this.id = id;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    private class Address{
        private String streetNb;
        private String streetName;
        private String apptNb;
        private String zipCode;
        private String stateNb;
        private String city;
        private String country;

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

    }
}
