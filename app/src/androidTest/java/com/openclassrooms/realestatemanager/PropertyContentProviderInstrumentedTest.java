package com.openclassrooms.realestatemanager;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.realestatemanager.database.RealEstateManagerDatabase;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.provider.PropertyContentProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class PropertyContentProviderInstrumentedTest {

    // FOR DATA
    private ContentResolver mContentResolver;

    // DATA SET FOR TEST
    private static long PROPERTY_ID = 100;

    @Before
    public void setUp() {
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                RealEstateManagerDatabase.class)
                .allowMainThreadQueries()
                .build();
        mContentResolver = InstrumentationRegistry.getContext().getContentResolver();
    }

    @Test
    public void insertAndGetItem() {
        // BEFORE : Adding demo item
        final Uri propertyUri = mContentResolver.insert(PropertyContentProvider.URI_PROPERTY, generateProperty());

        // TEST
        final Cursor cursor = mContentResolver.query(ContentUris.withAppendedId(PropertyContentProvider.URI_PROPERTY, PROPERTY_ID), null, null, null, null);
        assertThat(cursor, notNullValue());
        assertThat(cursor.getString(cursor.getColumnIndexOrThrow(Property.AREA_KEY)), is("Roscanvel"));
    }

    // ---

    private ContentValues generateProperty(){
        final ContentValues values = new ContentValues();
        values.put("id", 100);
        values.put(Property.AREA_KEY, "Roscanvel");
        values.put(Property.LATITUDE_KEY, 48.329944);
        values.put(Property.LONGITUDE_KEY, -4.549833);
        values.put(Property.PRICE_KEY, 100000);
        values.put(Property.SURFACE_KEY, 125);
        values.put(Property.NB_ROOMS_KEY, 6);
        values.put(Property.DESCRIPTION_KEY, "Awesome house to buy close to the sea");
        values.put(Property.PHOTO_URL_KEY, "https://picsum.photos/200/200/?image=820");
        values.put(Property.PHOTO_DESCRIPTION_KEY, "Garden");
        values.put(Property.IS_SOLD_KEY, false);
        values.put(Property.DATE_CREATED_KEY, 20180612);
        values.put(Property.DATE_SOLD_KEY, 0);
        values.put(Property.TYPE_KEY, "house");
        values.put(Property.POI_KEY, "school");
        values.put(Property.AGENT_ID_KEY, 3);
        return values;
    }
}
