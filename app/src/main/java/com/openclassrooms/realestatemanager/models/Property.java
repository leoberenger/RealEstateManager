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
    public static String PRICE_KEY = "price";
    public static String SURFACE_KEY = "surface";
    public static String NB_ROOMS_KEY = "nbRooms";
    public static String DESCRIPTION_KEY = "description";
    public static String PHOTO_URL_KEY = "urlPhoto";
    public static String PHOTO_DESCRIPTION_KEY = "photoDescription";
    public static String IS_SOLD_KEY = "isSold";
    public static String DATE_CREATED_KEY = "dateCreated";
    public static String DATE_SOLD_KEY = "dateSold";
    public static String TYPE_KEY = "type";
    public static String POI_KEY = "poi";
    public static String AGENT_ID_KEY = "agentId";


    @PrimaryKey(autoGenerate = true) private long id;
    private String area;
    private Double latitude;
    private Double longitude;
    private long price;
    private int surface;
    private int nbRooms;
    private String description;
    private String urlPhoto;
    private String photoDescription;
    private boolean isSold;
    private int dateCreated;
    private int dateSold;
    private String type;
    private String poi;
    private int agentID;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Property() { }

    public Property(String area, Double latitude, Double longitude, long price, int surface, int nbRooms, String description, String urlPhoto, String photoDescription, boolean isSold, int dateCreated, int dateSold, String type, String poi, int agentID) {
        this.id = id;
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.surface = surface;
        this.nbRooms = nbRooms;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.photoDescription = photoDescription;
        this.isSold = isSold;
        this.dateCreated = dateCreated;
        this.dateSold = dateSold;
        this.type = type;
        this.poi = poi;
        this.agentID = agentID;
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
    public long getPrice() {
        return price;
    }
    public int getSurface() {
        return surface;
    }
    public int getNbRooms() {
        return nbRooms;
    }
    public String getDescription() {
        return description;
    }
    public String getUrlPhoto() {
        return urlPhoto;
    }
    public String getPhotoDescription() {
        return photoDescription;
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
    public String getType() {
        return type;
    }
    public String getPoi() {
        return poi;
    }
    public int getAgentID() {
        return agentID;
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
    public void setPrice(long price) {
        this.price = price;
    }
    public void setSurface(int surface) {
        this.surface = surface;
    }
    public void setNbRooms(int nbRooms) {
        this.nbRooms = nbRooms;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
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
    public void setType(String type) {
        this.type = type;
    }
    public void setPoi(String poi) {
        this.poi = poi;
    }
    public void setAgentID(int agentID) {
        this.agentID = agentID;
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
        out.writeLong(price);
        out.writeInt(surface);
        out.writeInt(nbRooms);
        out.writeString(description);
        out.writeString(urlPhoto);
        out.writeString(photoDescription);
        out.writeInt((isSold)?1:0);
        out.writeInt(dateCreated);
        out.writeInt(dateSold);
        out.writeString(type);
        out.writeString(poi);
        out.writeInt(agentID);
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
        price = in.readLong();
        surface = in.readInt();
        nbRooms = in.readInt();
        description = in.readString();
        urlPhoto = in.readString();
        photoDescription = in.readString();
        isSold = in.readInt()!=0;
        dateCreated = in.readInt();
        dateSold = in.readInt();
        type = in.readString();
        poi = in.readString();
        agentID = in.readInt();
    }


    //---------------------------
    //CONTENT PROVIDER METHOD
    //--------------------------

    public static Property fromContentValues(ContentValues values) {
        final Property property = new Property();
        if (values.containsKey(AREA_KEY)) property.setArea(values.getAsString(AREA_KEY));
        if (values.containsKey(LATITUDE_KEY)) property.setLatitude(values.getAsDouble(LATITUDE_KEY));
        if (values.containsKey(LONGITUDE_KEY)) property.setLongitude(values.getAsDouble(LONGITUDE_KEY));
        if (values.containsKey(PRICE_KEY)) property.setPrice(values.getAsLong(PRICE_KEY));
        if (values.containsKey(SURFACE_KEY)) property.setSurface(values.getAsInteger(SURFACE_KEY));
        if (values.containsKey(NB_ROOMS_KEY)) property.setNbRooms(values.getAsInteger(NB_ROOMS_KEY));
        if (values.containsKey(DESCRIPTION_KEY)) property.setDescription(values.getAsString(DESCRIPTION_KEY));
        if (values.containsKey(PHOTO_URL_KEY)) property.setUrlPhoto(values.getAsString(PHOTO_URL_KEY));
        if (values.containsKey(PHOTO_DESCRIPTION_KEY)) property.setPhotoDescription(values.getAsString(PHOTO_DESCRIPTION_KEY));
        if (values.containsKey(IS_SOLD_KEY)) property.setSold(values.getAsBoolean(IS_SOLD_KEY));
        if (values.containsKey(DATE_CREATED_KEY)) property.setDateCreated(values.getAsInteger(DATE_CREATED_KEY));
        if (values.containsKey(DATE_SOLD_KEY)) property.setDateSold(values.getAsInteger(DATE_SOLD_KEY));
        if (values.containsKey(TYPE_KEY)) property.setType(values.getAsString(TYPE_KEY));
        if (values.containsKey(POI_KEY)) property.setPoi(values.getAsString(POI_KEY));
        if (values.containsKey(AGENT_ID_KEY)) property.setAgentID(values.getAsInteger(AGENT_ID_KEY));


        return property;
    }



}
