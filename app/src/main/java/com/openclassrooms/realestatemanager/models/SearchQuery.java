package com.openclassrooms.realestatemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class SearchQuery implements Parcelable {



    public static String SEARCH_QUERY_KEY = "query";

    private String area;
    private long priceMin;
    private long priceMax;
    private int surfaceMin;
    private int surfaceMax;
    private int nbRooms;
    private int nbPhotos;
    private boolean isSold;
    private int dateCreated;
    private int dateSold;
    private ArrayList<String> propertyTypes;
    private ArrayList<String> pois;


    //------------------------
    // CONSTRUCTOR
    //------------------------

    public SearchQuery(String area, long priceMin, long priceMax, int surfaceMin, int surfaceMax, int nbRooms, int nbPhotos, boolean isSold, int dateCreated, int dateSold, ArrayList<String> propertyTypes, ArrayList<String> pois) {
        this.area = area;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.surfaceMin = surfaceMin;
        this.surfaceMax = surfaceMax;
        this.nbRooms = nbRooms;
        this.nbPhotos = nbPhotos;
        this.isSold = isSold;
        this.dateCreated = dateCreated;
        this.dateSold = dateSold;
        this.propertyTypes = propertyTypes;
        this.pois = pois;
    }


    //------------------------
    //GETTERS
    //------------------------
    public String getArea() {
        return area;
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
    public int getDateCreated() {
        return dateCreated;
    }
    public int getDateSold() {
        return dateSold;
    }
    public ArrayList<String> getPropertyTypes() {
        return propertyTypes;
    }
    public ArrayList<String> getPois() {
        return pois;
    }

    //------------------------
    //SETTERS
    //------------------------

    public void setArea(String area) {
        this.area = area;
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
    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setDateSold(int dateSold) {
        this.dateSold = dateSold;
    }
    public void setPropertyTypes(ArrayList<String> propertyTypes) {
        this.propertyTypes = propertyTypes;
    }
    public void setPois(ArrayList<String> pois) {
        this.pois = pois;
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
        out.writeString(area);
        out.writeLong(priceMin);
        out.writeLong(priceMax);
        out.writeInt(surfaceMin);
        out.writeInt(surfaceMax);
        out.writeInt(nbRooms);
        out.writeInt(nbPhotos);
        out.writeInt(((isSold)?1:0));
        out.writeInt(dateCreated);
        out.writeInt(dateSold);
        out.writeList(propertyTypes);
        out.writeList(pois);
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
        area = in.readString();
        priceMin = in.readLong();
        priceMax = in.readLong();
        surfaceMin = in.readInt();
        surfaceMax = in.readInt();
        nbRooms = in.readInt();
        nbPhotos = in.readInt();
        isSold = in.readInt()!=0;
        dateCreated = in.readInt();
        dateSold = in.readInt();
        propertyTypes = new ArrayList<String>();
        in.readStringList(propertyTypes);
        pois = new ArrayList<String>();
        in.readStringList(pois);
    }


}
