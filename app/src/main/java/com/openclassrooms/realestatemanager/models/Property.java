package com.openclassrooms.realestatemanager.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

@Entity
public class Property implements Parcelable {

    public static String AREA_KEY = "area";
    public static String LATITUDE_KEY = "latitude";
    public static String LONGITUDE_KEY = "longitude";

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


    //---------------------------
    //PARCELABLE METHODS
    //--------------------------

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(area);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
    }

    public static final Parcelable.Creator<Property> CREATOR
            = new Parcelable.Creator<Property>() {

        public Property createFromParcel(Parcel in) {
            return new Property(in);
        }

        public Property[] newArray(int size) {
            return new Property[size];
        }
    };

    private Property(Parcel in) {
        id = in.readLong();
        area = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
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

    //----UTILS-----
    public static Property fromContentValues(ContentValues values) {
        final Property property = new Property();
        if (values.containsKey(AREA_KEY)) property.setArea(values.getAsString(AREA_KEY));
        if (values.containsKey(LATITUDE_KEY)) property.setLatitude(values.getAsDouble(LATITUDE_KEY));
        if (values.containsKey(LONGITUDE_KEY)) property.setLongitude(values.getAsDouble(LONGITUDE_KEY));
        return property;
    }
}
