package com.openclassrooms.realestatemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchQuery implements Parcelable {

    public static String SEARCH_QUERY_KEY = "query";

    private String [] areas;
    private long priceMin;
    private long priceMax;
    private int surfaceMin;
    private int surfaceMax;
    private int nbRooms;
    private int nbPhotos;
    private boolean isSold;
    private int date;
    private String propertyType;
    private int [] propertyPOIs;


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public SearchQuery(String [] areas, long priceMin, long priceMax, int surfaceMin, int surfaceMax,
                       int nbRooms, int nbPhotos, boolean isSold, int date, String propertyType,
                       int [] propertyPOIs){
        this.areas = areas;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.surfaceMin = surfaceMin;
        this.surfaceMax = surfaceMax;
        this.nbRooms = nbRooms;
        this.nbPhotos = nbPhotos;
        this.isSold = isSold;
        this.date = date;
        this.propertyType = propertyType;
        this.propertyPOIs = propertyPOIs;
    }


    //------------------------
    //GETTERS
    //------------------------
    public String [] getAreas() {
        return areas;
    }
    public long getPriceMin() {
        return priceMin;
    }
    public long getPriceMax() {
        return priceMax;
    }
    public int getSurfaceMin() {
        return surfaceMin;
    }
    public int getSurfaceMax() {
        return surfaceMax;
    }
    public int getNbRooms() {
        return nbRooms;
    }
    public int getNbPhotos() {
        return nbPhotos;
    }
    public boolean isSold() {
        return isSold;
    }
    public int getDate() {
        return date;
    }
    public String getPropertyType() {
        return propertyType;
    }
    public int [] getPropertyPOIs() {
        return propertyPOIs;
    }


    //------------------------
    //SETTERS
    //------------------------

    public void setAreas(String [] areas) {
        this.areas = areas;
    }    public void setPriceMin(long priceMin) {
        this.priceMin = priceMin;
    }
    public void setPriceMax(long priceMax) {
        this.priceMax = priceMax;
    }
    public void setSurfaceMin(int surfaceMin) {
        this.surfaceMin = surfaceMin;
    }
    public void setSurfaceMax(int surfaceMax) {
        this.surfaceMax = surfaceMax;
    }
    public void setNbRooms(int nbRooms) {
        this.nbRooms = nbRooms;
    }
    public void setNbPhotos(int nbPhotos) {
        this.nbPhotos = nbPhotos;
    }
    public void setSold(boolean sold) {
        isSold = sold;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
    public void setPois(int [] propertyPOIs) {
        this.propertyPOIs = propertyPOIs;
    }



    //------------------------
    //PARCELABLE METHODS
    //------------------------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeStringArray(areas);
        out.writeLong(priceMin);
        out.writeLong(priceMax);
        out.writeInt(surfaceMin);
        out.writeInt(surfaceMax);
        out.writeInt(nbRooms);
        out.writeInt(nbPhotos);
        out.writeInt(((isSold)?1:0));
        out.writeInt(date);
        out.writeString(propertyType);
        out.writeIntArray(propertyPOIs);
    }

    public static final Parcelable.Creator<SearchQuery> CREATOR
            = new Parcelable.Creator<SearchQuery>() {

        public SearchQuery createFromParcel(Parcel in) {
            return new SearchQuery(in);
        }

        public SearchQuery[] newArray(int size) {
            return new SearchQuery[size];
        }
    };

    private SearchQuery(Parcel in) {
        areas = new String [3];
        in.readStringArray(areas);
        priceMin = in.readLong();
        priceMax = in.readLong();
        surfaceMin = in.readInt();
        surfaceMax = in.readInt();
        nbRooms = in.readInt();
        nbPhotos = in.readInt();
        isSold = in.readInt()!=0;
        date = in.readInt();
        propertyType = in.readString();
        propertyPOIs = new int[4];
        in.readIntArray(propertyPOIs);
    }
}
