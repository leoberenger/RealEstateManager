package com.openclassrooms.realestatemanager.models;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = Agent.class,
        parentColumns = "id",
        childColumns = "agent_id"))
public class Property{

    @PrimaryKey(autoGenerate = true) private long id;
    private String name;
    private String type;
    private int prix;
    private int surface;
    private int nbRooms;
    private String description;
    private String photoURI;
    private String photoDescription;
    private List<String> nearbyPOI;
    private boolean sold;
    private Date date_created;
    private Date date_sold;
    private long agent_id;
    private Address address;
    private Double latitude;
    private Double longitude;

    //---------------------------
    //CONSTRUCTORS
    //--------------------------

    public Property() { }

    public Property(String name, long agent_id, boolean sold, Date date_created, Address address, Double latitude, Double longitude) {
        this.name = name;
        this.agent_id = agent_id;
        this.sold = sold;
        this.date_created = date_created;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //---------------------------
    //GETTERS
    //--------------------------

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public int getPrix() {
        return prix;
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
    public String getPhotoURI() {
        return photoURI;
    }
    public String getPhotoDescription() {
        return photoDescription;
    }
    public List<String> getNearbyPOI() {
        return nearbyPOI;
    }
    public boolean isSold() {
        return sold;
    }
    public Date getDate_created() {
        return date_created;
    }
    public Date getDate_sold() {
        return date_sold;
    }
    public long getAgent_id() {
        return agent_id;
    }
    public Address getAddress() {
        return address;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setPrix(int prix) {
        this.prix = prix;
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
    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }
    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }
    public void setNearbyPOI(List<String> nearbyPOI) {
        this.nearbyPOI = nearbyPOI;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }
    public void setDate_sold(Date date_sold) {
        this.date_sold = date_sold;
    }
    public void setAgent_id(long agent_id) {
    this.agent_id = agent_id;
}
    public void setAddress(Address address) {
        this.address = address;
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
