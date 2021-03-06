package com.openclassrooms.realestatemanager;

import android.arch.persistence.room.Room;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.realestatemanager.database.RealEstateManagerDatabase;
import com.openclassrooms.realestatemanager.models.Address;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.provider.PropertyContentProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    private static final long PROPERTY_ID = 101;

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
        final Uri propertyUri = mContentResolver.insert(PropertyContentProvider.URI_PROPERTY, generateProperty());
    }

    // ---

    private ContentValues generateProperty(){
        final ContentValues values = new ContentValues();
        values.put("id", PROPERTY_ID);
        values.put(Property.AREA_KEY, "Roscanvel");
        values.put(Property.LATITUDE_KEY, 48.329944);
        values.put(Property.LONGITUDE_KEY, -4.549833);
        values.put(Property.PRICE_KEY, 100000);
        values.put(Property.SURFACE_KEY, 125);
        values.put(Property.NB_ROOMS_KEY, 6);
        values.put(Property.DESCRIPTION_KEY, "Awesome house to buy close to the sea");
        values.put(Property.PHOTO_URL_KEY, "https://picsum.photos/200/200/?image=820");
        values.put(Property.PHOTO_DESCRIPTION_KEY, "Garden");
        values.put(Property.NB_PHOTOS_KEY, 1);

        values.put(Property.IS_SOLD_KEY, 0);
        values.put(Property.DATE_CREATED_KEY, 20180612);
        values.put(Property.DATE_SOLD_KEY, 0);
        values.put(Property.TYPE_KEY, "House");
        values.put(Property.AGENT_ID_KEY, 2);

        values.put(Property.POI_SCHOOL_KEY, 1);
        values.put(Property.POI_PARK_KEY, 0);
        values.put(Property.POI_SHOPPING_KEY, 1);
        values.put(Property.POI_METRO_KEY, 0);

        values.put(Address.ADDRESS_STREET_NB_KEY, "2");
        values.put(Address.ADDRESS_STREET_NAME_KEY, "Glasgow");
        values.put(Address.ADDRESS_APPT_NUMBER_KEY, "20");
        values.put(Address.ADDRESS_ZIP_CODE_KEY, "29200");
        values.put(Address.ADDRESS_CITY_KEY, "Brest");
        values.put(Address.ADDRESS_COUNTRY_KEY, "France");

        return values;
    }
}
