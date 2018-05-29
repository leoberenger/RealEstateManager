package com.openclassrooms.realestatemanager;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.realestatemanager.database.dao.RealEstateManagerDatabase;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PropertyDAOInstrumentedTest {

    //FOR DATA
    private RealEstateManagerDatabase database;

    //DATA SET FOR TEST
    private static long PROPERTY_ID = 2;
    private static Property PROPERTY_DEMO = new Property(PROPERTY_ID, "Empire State Building",40.748441, -73.985664);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception{
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RealEstateManagerDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    public void closeDb() throws Exception{
        database.close();
    }

    @Test
    public void insertAndGetProperty() throws InterruptedException{
        this.database.propertyDao().insertProperty(PROPERTY_DEMO);
        Property property =
                LiveDataTestUtil.getValue(this.database.propertyDao().getProperty(PROPERTY_ID));
        assertTrue(property.getName().equals(PROPERTY_DEMO.getName()) && property.getId() == PROPERTY_ID);
    }


}