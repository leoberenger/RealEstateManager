package com.openclassrooms.realestatemanager.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.openclassrooms.realestatemanager.database.dao.AgentDAO;
import com.openclassrooms.realestatemanager.database.dao.PropertyDAO;
import com.openclassrooms.realestatemanager.models.Agent;
import com.openclassrooms.realestatemanager.models.Property;

@Database(entities = {Property.class, Agent.class}, version = 1, exportSchema = false)
public abstract class RealEstateManagerDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile RealEstateManagerDatabase INSTANCE;

    // --- DAO ---
    public abstract PropertyDAO propertyDao();
    public abstract AgentDAO agentDao();


    // --- INSTANCE ---
    public static RealEstateManagerDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (RealEstateManagerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RealEstateManagerDatabase.class, "MyDatabase.db")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase(){
        return new Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Property property0 = new Property(
                        "Port de commerce",
                        48.392151,
                        -4.479032,
                        95000,
                        125,
                        7,
                        "Très bel appartement en bord de mer, proche de tous commerces",
                        "https://picsum.photos/200/200/?image=88",
                        "vue du salon",
                        false,
                        20180201,
                        0,
                        "appartment",
                        "shopping",
                        3);

                Property property1 = new Property(
                        "Centre ville",
                        48.380143,
                        -4.487213,
                        250000,
                        215,
                        11,
                        "Au centre ville, maison de charme pour grande famille avec jardin",
                        "https://picsum.photos/200/200/?image=93",
                        "vue du jardin",
                        false,
                        20180401,
                        0,
                        "house",
                        "school",
                        1);

                Property property2 = new Property(
                        "Centre ville",
                        48.406232,
                        -4.496259,
                        328000,
                        120,
                        4,
                        "Immense loft au coeur du centre ville, parfait pour créateurs de startup",
                        "https://picsum.photos/200/200/?image=11",
                        "vue de la terrasse",
                        true,
                        20180131,
                        20180401,
                        "penthouse",
                        "metro",
                        2);


                Property [] properties = {property0, property1, property2};

/*
                ContentValues [] contentValues = new ContentValues [3];

                for(int i = 0; i<contentValues.length; i++){
                    contentValues[i].put(Property.AREA_KEY, properties[i].getArea());
                    contentValues[i].put(Property.LATITUDE_KEY, properties[i].getLatitude());
                    contentValues[i].put(Property.LONGITUDE_KEY, properties[i].getLongitude());
                    contentValues[i].put(Property.PRICE_KEY, properties[i].getPrice());
                    contentValues[i].put(Property.SURFACE_KEY, properties[i].getSurface());
                    contentValues[i].put(Property.NB_ROOMS_KEY, properties[i].getNbRooms());
                    contentValues[i].put(Property.DESCRIPTION_KEY, properties[i].getDescription());
                    contentValues[i].put(Property.PHOTO_URL_KEY, properties[i].getUrlPhoto());
                    contentValues[i].put(Property.PHOTO_DESCRIPTION_KEY, properties[i].getPhotoDescription());
                    contentValues[i].put(Property.IS_SOLD_KEY, properties[i].isSold());
                    contentValues[i].put(Property.DATE_CREATED_KEY, properties[i].getDateCreated());
                    contentValues[i].put(Property.DATE_SOLD_KEY, properties[i].getDateSold());
                    contentValues[i].put(Property.TYPE_KEY, properties[i].getType());
                    contentValues[i].put(Property.POI_KEY, properties[i].getPoi());
                    contentValues[i].put(Property.AGENT_ID_KEY, properties[i].getAgentID());

                    db.insert("Property", OnConflictStrategy.IGNORE, contentValues[i]);
                }
*/

                ContentValues contentValues0 = new ContentValues();
                contentValues0.put(Property.AREA_KEY, properties[0].getArea());
                contentValues0.put(Property.LATITUDE_KEY, properties[0].getLatitude());
                contentValues0.put(Property.LONGITUDE_KEY, properties[0].getLongitude());
                contentValues0.put(Property.PRICE_KEY, properties[0].getPrice());
                contentValues0.put(Property.SURFACE_KEY, properties[0].getSurface());
                contentValues0.put(Property.NB_ROOMS_KEY, properties[0].getNbRooms());
                contentValues0.put(Property.DESCRIPTION_KEY, properties[0].getDescription());
                contentValues0.put(Property.PHOTO_URL_KEY, properties[0].getUrlPhoto());
                contentValues0.put(Property.PHOTO_DESCRIPTION_KEY, properties[0].getPhotoDescription());
                contentValues0.put(Property.IS_SOLD_KEY, properties[0].isSold());
                contentValues0.put(Property.DATE_CREATED_KEY, properties[0].getDateCreated());
                contentValues0.put(Property.DATE_SOLD_KEY, properties[0].getDateSold());
                contentValues0.put(Property.TYPE_KEY, properties[0].getType());
                contentValues0.put(Property.POI_KEY, properties[0].getPoi());
                contentValues0.put(Property.AGENT_ID_KEY, properties[0].getAgentID());

                db.insert("Property", OnConflictStrategy.IGNORE, contentValues0);


                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(Property.AREA_KEY, properties[1].getArea());
                contentValues1.put(Property.LATITUDE_KEY, properties[1].getLatitude());
                contentValues1.put(Property.LONGITUDE_KEY, properties[1].getLongitude());
                contentValues1.put(Property.PRICE_KEY, properties[1].getPrice());
                contentValues1.put(Property.SURFACE_KEY, properties[1].getSurface());
                contentValues1.put(Property.NB_ROOMS_KEY, properties[1].getNbRooms());
                contentValues1.put(Property.DESCRIPTION_KEY, properties[1].getDescription());
                contentValues1.put(Property.PHOTO_URL_KEY, properties[1].getUrlPhoto());
                contentValues1.put(Property.PHOTO_DESCRIPTION_KEY, properties[1].getPhotoDescription());
                contentValues1.put(Property.IS_SOLD_KEY, properties[1].isSold());
                contentValues1.put(Property.DATE_CREATED_KEY, properties[1].getDateCreated());
                contentValues1.put(Property.DATE_SOLD_KEY, properties[1].getDateSold());
                contentValues1.put(Property.TYPE_KEY, properties[1].getType());
                contentValues1.put(Property.POI_KEY, properties[1].getPoi());
                contentValues1.put(Property.AGENT_ID_KEY, properties[1].getAgentID());

                db.insert("Property", OnConflictStrategy.IGNORE, contentValues1);

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(Property.AREA_KEY, properties[2].getArea());
                contentValues2.put(Property.LATITUDE_KEY, properties[2].getLatitude());
                contentValues2.put(Property.LONGITUDE_KEY, properties[2].getLongitude());
                contentValues2.put(Property.PRICE_KEY, properties[2].getPrice());
                contentValues2.put(Property.SURFACE_KEY, properties[2].getSurface());
                contentValues2.put(Property.NB_ROOMS_KEY, properties[2].getNbRooms());
                contentValues2.put(Property.DESCRIPTION_KEY, properties[2].getDescription());
                contentValues2.put(Property.PHOTO_URL_KEY, properties[2].getUrlPhoto());
                contentValues2.put(Property.PHOTO_DESCRIPTION_KEY, properties[2].getPhotoDescription());
                contentValues2.put(Property.IS_SOLD_KEY, properties[2].isSold());
                contentValues2.put(Property.DATE_CREATED_KEY, properties[2].getDateCreated());
                contentValues2.put(Property.DATE_SOLD_KEY, properties[2].getDateSold());
                contentValues2.put(Property.TYPE_KEY, properties[2].getType());
                contentValues2.put(Property.POI_KEY, properties[2].getPoi());
                contentValues2.put(Property.AGENT_ID_KEY, properties[2].getAgentID());

                db.insert("Property", OnConflictStrategy.IGNORE, contentValues2);


            }
        };
    }

    public RealEstateManagerDatabase() {
    }

}
