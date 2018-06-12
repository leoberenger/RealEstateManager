package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.EditionFragment;
import com.openclassrooms.realestatemanager.models.Property;

import butterknife.BindView;

public class EditionActivity extends AppCompatActivity {

    String TAG = "EditionActivity";
    public static String PROPERTY_KEY = "PROPERTY";
    public static String EDITION_KEY = "EDITION";
    private boolean isEditionMode = false;

    //FOR DATA
    private Property property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        //Retrieve property to edit
        if(getIntent().getParcelableExtra(MainActivity.PROPERTY_KEY)!=null){
            property = getIntent().getParcelableExtra(MainActivity.PROPERTY_KEY);
            isEditionMode = true;
        }

        this.configureAndShowEditionFragment(isEditionMode);
    }

    private void  configureAndShowEditionFragment(boolean isEditionMode){
        EditionFragment fragment = (EditionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_edition_layout);

        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(EDITION_KEY, isEditionMode);

            //if Edition of Property, send property
            if(isEditionMode) {
                bundle.putParcelable(PROPERTY_KEY, property);
            }

            fragment = new EditionFragment();
            fragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_edition_fragment, fragment)
                    .commit();
            }
    }
}
