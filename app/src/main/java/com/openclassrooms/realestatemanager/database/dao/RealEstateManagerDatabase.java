package com.openclassrooms.realestatemanager.database.dao;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.openclassrooms.realestatemanager.models.Agent;
import com.openclassrooms.realestatemanager.models.Property;

import java.util.ArrayList;
import java.util.List;

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

                ContentValues contentValues = new ContentValues();
                contentValues.put("id", 1);
                contentValues.put("name", "Propriété 1");
                contentValues.put("type", "Appartment");
                contentValues.put("prix", 10000);
                contentValues.put("surface", 30);
                contentValues.put("nbRooms", 3);
                contentValues.put("description", "Great appartment at the top of the building. Close to all services");
                contentValues.put("photoURI", "https://picsum.photos/200/200/?image=826");
                contentValues.put("photoDescription", "Photo of the appartment");
                contentValues.put("nearbyPOI", "School");
                contentValues.put("sold", false);
                contentValues.put("id", 1);
                contentValues.put("date_created", 20180529);
                contentValues.put("date_sold", "");
                contentValues.put("agent_id", 1);
                contentValues.put("Address", "");
                contentValues.put("latitude", 40.751621);
                contentValues.put("longitude", -73.975502);

                db.insert("Property", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

    public RealEstateManagerDatabase() {
    }

}
