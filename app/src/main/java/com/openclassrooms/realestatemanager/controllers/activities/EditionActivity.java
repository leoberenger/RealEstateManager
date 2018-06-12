package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

public class EditionActivity extends AppCompatActivity {

    String TAG ="EditionActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        Property property = getIntent().getParcelableExtra(MainActivity.PROPERTY_KEY);

        Log.e(TAG, "p selected = " + property.getArea());

    }
}
