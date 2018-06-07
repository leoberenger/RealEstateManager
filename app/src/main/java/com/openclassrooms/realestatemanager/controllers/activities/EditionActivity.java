package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.openclassrooms.realestatemanager.R;

public class EditionActivity extends AppCompatActivity {

    String PROPERTY_ID = "PROPERTY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        Intent intent = getIntent();
        long propertyId = intent.getLongExtra(PROPERTY_ID, -1);
        Log.e("EditionActivity", "propertyId = " + propertyId);

    }
}
