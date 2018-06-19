package com.openclassrooms.realestatemanager.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.openclassrooms.realestatemanager.models.Property;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PropertyDAO {

    @Query("SELECT * FROM Property")
    LiveData<List<Property>> getAllProperties();

    @Query("SELECT * FROM Property WHERE area IN (:areas) " +
            "AND price BETWEEN :priceMin AND :priceMax " +
            "AND surface BETWEEN :surfaceMin AND :surfaceMax " +
            "AND nbRooms >= :nbRooms " +
            "AND nbPhotos >= :nbPhotos " +
            "AND poiSchool >= :poi0 " +
            "AND poiPark >= :poi1 " +
            "AND poiShopping >= :poi2 " +
            "AND poiMetro >= :poi3 " +
            "AND dateCreated >= :dateCreated " +
            "AND dateSold >= :dateSold " +
            "AND isSold = :isSold " +
            "AND type = :propertyType "
            )

    LiveData<List<Property>> getSearchedProperties(
            String [] areas, long priceMin, long priceMax,
            int surfaceMin, int surfaceMax, int nbRooms,
            int nbPhotos, boolean isSold, int dateCreated, int dateSold,
            String propertyType, int poi0, int poi1, int poi2, int poi3
    );

    @Query("SELECT * FROM Property")
    Cursor getPropertiesWithCursor();

    @Query("SELECT * FROM Property WHERE id = :propertyId")
    LiveData<Property> getProperty(long propertyId);

    @Insert
    long insertProperty(Property property);

    @Update
    int updateProperty(Property property);
}
