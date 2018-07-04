package com.openclassrooms.realestatemanager.models;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_APPT_NUMBER_KEY;
import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_CITY_KEY;
import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_COUNTRY_KEY;
import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_STREET_NAME_KEY;
import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_STREET_NB_KEY;
import static com.openclassrooms.realestatemanager.models.Address.ADDRESS_ZIP_CODE_KEY;

@Entity
public class Property implements Parcelable {

    public static final String PROPERTY_KEY = "property";
    public static final String PROPERTIES_KEY = "properties";
    public static final String PROPERTY_ID = "PROPERTY_ID";

    public static final String AREA_KEY = "area";
    public static final String LATITUDE_KEY = "latitude";
    public static final String LONGITUDE_KEY = "longitude";
    public static final String PRICE_KEY = "price";
    public static final String SURFACE_KEY = "surface";
    public static final String NB_ROOMS_KEY = "nbRooms";
    public static final String DESCRIPTION_KEY = "description";
    public static final String PHOTO_URL_KEY = "photoUrls";
    public static final String PHOTO_DESCRIPTION_KEY = "photoDescriptions";
    public static final String NB_PHOTOS_KEY = "nbPhotos";

    public static final String IS_SOLD_KEY = "isSold";
    public static final String DATE_CREATED_KEY = "dateCreated";
    public static final String DATE_SOLD_KEY = "dateSold";
    public static final String TYPE_KEY = "type";
    public static final String AGENT_ID_KEY = "agentId";
    public static final String POI_SCHOOL_KEY = "poiSchool";
    public static final String POI_PARK_KEY = "poiPark";
    public static final String POI_SHOPPING_KEY = "poiShopping";
    public static final String POI_METRO_KEY = "poiMetro";

    public static final String[] typesNames = {"House", "Apartment", "Duplex", "Penthouse"};

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String area;
    private Double latitude;
    private Double longitude;
    private long price;
    private int surface;

    private int nbRooms;
    private String description;
    private String photoUrl;
    private String photoDescription;
    private int nbPhotos;
    private int isSold;

    private int dateCreated;
    private int dateSold;
    private String type;
    private int agentID;

    private int poiSchool;
    private int poiPark;
    private int poiShopping;
    private int poiMetro;

    @Embedded
    private Address address;


    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Property() { }

    public Property(String area, Double latitude, Double longitude, long price, int surface,
                    int nbRooms, String description, String photoUrl,
                    String photoDescription, int nbPhotos, int isSold,
                    int dateCreated, int dateSold, String type, int agentID,
                    int poiSchool, int poiPark, int poiShopping, int poiMetro, Address address) {
        this.area = area;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.surface = surface;
        this.nbRooms = nbRooms;
        this.description = description;
        this.photoUrl = photoUrl;
        this.photoDescription = photoDescription;
        this.nbPhotos = nbPhotos;
        this.isSold = isSold;
        this.dateCreated = dateCreated;
        this.dateSold = dateSold;
        this.type = type;
        this.agentID = agentID;
        this.poiSchool = poiSchool;
        this.poiPark = poiPark;
        this.poiShopping = poiShopping;
        this.poiMetro = poiMetro;
        this.address = address;
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
    public String getPhotoUrl() {
        return photoUrl;
    }
    public String getPhotoDescription() {
        return photoDescription;
    }
    public int getNbPhotos() {
        return nbPhotos;
    }
    public int isSold() {
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
    public int getAgentID() {
        return agentID;
    }
    public int getPoiSchool() {
        return poiSchool;
    }
    public int getPoiPark() {
        return poiPark;
    }
    public int getPoiShopping() {
        return poiShopping;
    }
    public int getPoiMetro() {
        return poiMetro;
    }
    public Address getAddress() {
        return address;
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
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }
    public void setNbPhotos(int nbPhotos) {
        this.nbPhotos = nbPhotos;
    }
    public void setIsSold(int isSold) {
        this.isSold = isSold;
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
    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }
    public void setPoiSchool(int poiSchool) {
        this.poiSchool = poiSchool;
    }
    public void setPoiPark(int poiPark) {
        this.poiPark = poiPark;
    }
    public void setPoiShopping(int poiShopping) {
        this.poiShopping = poiShopping;
    }
    public void setPoiMetro(int poiMetro) {
        this.poiMetro = poiMetro;
    }
    public void setAddress(Address address) {
        this.address = address;
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
        out.writeInt(nbPhotos);
        out.writeInt(isSold);
        out.writeInt(dateCreated);
        out.writeInt(dateSold);
        out.writeString(type);
        out.writeInt(agentID);

        out.writeInt(poiSchool);
        out.writeInt(poiPark);
        out.writeInt(poiShopping);
        out.writeInt(poiMetro);

        out.writeParcelable(address, flags);

        out.writeString(photoUrl);
        out.writeString(photoDescription);
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
        nbPhotos = in.readInt();
        isSold = in.readInt();
        dateCreated = in.readInt();
        dateSold = in.readInt();
        type = in.readString();
        agentID = in.readInt();

        poiSchool = in.readInt();
        poiPark = in.readInt();
        poiShopping = in.readInt();
        poiMetro = in.readInt();

        address = in.readParcelable(getClass().getClassLoader());

        photoUrl = in.readString();
        photoDescription = in.readString();
    }


    //---------------------------
    //CONTENT PROVIDER METHOD
    //--------------------------

    public static Property fromContentValues(ContentValues values) {
        final Property property = new Property();
        final Address address = new Address();

        if (values.containsKey(AREA_KEY)) property.setArea(values.getAsString(AREA_KEY));
        if (values.containsKey(LATITUDE_KEY)) property.setLatitude(values.getAsDouble(LATITUDE_KEY));
        if (values.containsKey(LONGITUDE_KEY)) property.setLongitude(values.getAsDouble(LONGITUDE_KEY));
        if (values.containsKey(PRICE_KEY)) property.setPrice(values.getAsLong(PRICE_KEY));
        if (values.containsKey(SURFACE_KEY)) property.setSurface(values.getAsInteger(SURFACE_KEY));
        if (values.containsKey(NB_ROOMS_KEY)) property.setNbRooms(values.getAsInteger(NB_ROOMS_KEY));
        if (values.containsKey(DESCRIPTION_KEY)) property.setDescription(values.getAsString(DESCRIPTION_KEY));
        if (values.containsKey(PHOTO_URL_KEY)) property.setPhotoUrl(values.getAsString(PHOTO_URL_KEY));
        if (values.containsKey(PHOTO_DESCRIPTION_KEY)) property.setPhotoDescription(values.getAsString(PHOTO_DESCRIPTION_KEY));
        if (values.containsKey(NB_PHOTOS_KEY)) property.setNbPhotos(values.getAsInteger(NB_PHOTOS_KEY));

        if (values.containsKey(IS_SOLD_KEY)) property.setIsSold(values.getAsInteger(IS_SOLD_KEY));
        if (values.containsKey(DATE_CREATED_KEY)) property.setDateCreated(values.getAsInteger(DATE_CREATED_KEY));
        if (values.containsKey(DATE_SOLD_KEY)) property.setDateSold(values.getAsInteger(DATE_SOLD_KEY));
        if (values.containsKey(TYPE_KEY)) property.setType(values.getAsString(TYPE_KEY));
        if (values.containsKey(AGENT_ID_KEY)) property.setAgentID(values.getAsInteger(AGENT_ID_KEY));

        if (values.containsKey(POI_SCHOOL_KEY)) property.setPoiSchool(values.getAsInteger(POI_SCHOOL_KEY));
        if (values.containsKey(POI_PARK_KEY)) property.setPoiPark(values.getAsInteger(POI_PARK_KEY));
        if (values.containsKey(POI_SHOPPING_KEY)) property.setPoiShopping(values.getAsInteger(POI_SHOPPING_KEY));
        if (values.containsKey(POI_METRO_KEY)) property.setPoiMetro(values.getAsInteger(POI_METRO_KEY));

        if (values.containsKey(ADDRESS_STREET_NB_KEY)) address.setStreetNb(values.getAsString(ADDRESS_STREET_NB_KEY));
        if (values.containsKey(ADDRESS_STREET_NAME_KEY)) address.setStreetName(values.getAsString(ADDRESS_STREET_NAME_KEY));
        if (values.containsKey(ADDRESS_APPT_NUMBER_KEY)) address.setApptNb(values.getAsString(ADDRESS_APPT_NUMBER_KEY));
        if (values.containsKey(ADDRESS_ZIP_CODE_KEY)) address.setZipCode(values.getAsString(ADDRESS_ZIP_CODE_KEY));
        if (values.containsKey(ADDRESS_CITY_KEY)) address.setCity(values.getAsString(ADDRESS_CITY_KEY));
        if (values.containsKey(ADDRESS_COUNTRY_KEY)) address.setCountry(values.getAsString(ADDRESS_COUNTRY_KEY));

        property.setAddress(address);

        return property;
    }


}
