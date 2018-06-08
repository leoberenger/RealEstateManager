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

                Property property0 = new Property(0, "Centre ville", 48.387933, -4.488045);
                Property property1 = new Property(1, "Port de commerce", 48.392151, -4.479032);
                Property property2 = new Property(2, "St Marc", 48.386929, -4.467273);
                Property property3 = new Property(3, "St Martin", 48.380143, -4.487213);
                Property property4 = new Property(4, "Bellevue", 48.402719, -4.467158);
                Property property5 = new Property(5, "St Pierre", 48.406232, -4.496259);
                Property property6 = new Property(6, "Europe", 48.408568, -4.513296);
                Property property7 = new Property(7, "Lambezellec", 48.412385, -4.480294);
                Property property8 = new Property(8, "Gouesnou", 48.414408, -4.451433);
                Property property9 = new Property(9, "Lampaul", 48.396350, -4.418772);

                Property [] properties = {property0, property1, property2, property3, property4, property5,
                    property6, property7, property8, property9};

                /*
                ContentValues [] contentValues = new ContentValues[10];

                for(int i = 0; i<contentValues.length; i++){
                    contentValues[i].put("id", properties[i].getId());
                    contentValues[i].put("area", properties[i].getArea());
                    contentValues[i].put("latitude", properties[i].getLatitude());
                    contentValues[i].put("longitude", properties[i].getLongitude());

                    db.insert("Property", OnConflictStrategy.IGNORE, contentValues[i]);
                }
                */

                ContentValues contentValues0 = new ContentValues();
                contentValues0.put("id", properties[0].getId());
                contentValues0.put("area", properties[0].getArea());
                contentValues0.put("latitude", properties[0].getLatitude());
                contentValues0.put("longitude", properties[0].getLongitude());

                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("id", properties[1].getId());
                contentValues1.put("area", properties[1].getArea());
                contentValues1.put("latitude", properties[1].getLatitude());
                contentValues1.put("longitude", properties[1].getLongitude());

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("id", properties[2].getId());
                contentValues2.put("area", properties[2].getArea());
                contentValues2.put("latitude", properties[2].getLatitude());
                contentValues2.put("longitude", properties[2].getLongitude());

                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("id", properties[3].getId());
                contentValues3.put("area", properties[3].getArea());
                contentValues3.put("latitude", properties[3].getLatitude());
                contentValues3.put("longitude", properties[3].getLongitude());

                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("id", properties[4].getId());
                contentValues4.put("area", properties[4].getArea());
                contentValues4.put("latitude", properties[4].getLatitude());
                contentValues4.put("longitude", properties[4].getLongitude());

                ContentValues contentValues5 = new ContentValues();
                contentValues5.put("id", properties[5].getId());
                contentValues5.put("area", properties[5].getArea());
                contentValues5.put("latitude", properties[5].getLatitude());
                contentValues5.put("longitude", properties[5].getLongitude());

                ContentValues contentValues6 = new ContentValues();
                contentValues6.put("id", properties[6].getId());
                contentValues6.put("area", properties[6].getArea());
                contentValues6.put("latitude", properties[6].getLatitude());
                contentValues6.put("longitude", properties[6].getLongitude());

                ContentValues contentValues7 = new ContentValues();
                contentValues7.put("id", properties[7].getId());
                contentValues7.put("area", properties[7].getArea());
                contentValues7.put("latitude", properties[7].getLatitude());
                contentValues7.put("longitude", properties[7].getLongitude());

                ContentValues contentValues8 = new ContentValues();
                contentValues8.put("id", properties[8].getId());
                contentValues8.put("area", properties[8].getArea());
                contentValues8.put("latitude", properties[8].getLatitude());
                contentValues8.put("longitude", properties[8].getLongitude());

                ContentValues contentValues9 = new ContentValues();
                contentValues9.put("id", properties[9].getId());
                contentValues9.put("area", properties[9].getArea());
                contentValues9.put("latitude", properties[9].getLatitude());
                contentValues9.put("longitude", properties[9].getLongitude());


                ContentValues [] contentValues = {contentValues0, contentValues1, contentValues2, contentValues3, contentValues4,
                        contentValues5, contentValues6, contentValues7, contentValues8, contentValues9};

                for(int i = 0; i<10; i++){
                    db.insert("Property", OnConflictStrategy.IGNORE, contentValues[i]);
                }
            }
        };
    }

    public RealEstateManagerDatabase() {
    }

}
