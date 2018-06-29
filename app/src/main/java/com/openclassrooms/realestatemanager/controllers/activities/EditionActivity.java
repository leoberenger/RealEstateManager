package com.openclassrooms.realestatemanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.EditionFragment;
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

public class EditionActivity extends AppCompatActivity
    implements EditionFragment.OnEditionListener{

    private final String TAG = "EditionActivity";
    public static final String PROPERTY_KEY = "PROPERTY";
    public static final String EDITION_KEY = "EDITION";
    private boolean isEditionMode = false;

    //FOR DATA
    private PropertyViewModel propertyViewModel;
    private Property property;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        this.configureViewModel();

        //if Edit, retrieve property
        if(getIntent().getParcelableExtra(Property.PROPERTY_KEY) != null){
            property = getIntent().getParcelableExtra(Property.PROPERTY_KEY);
            isEditionMode = true;
        }

        this.configureAndShowEditionFragment(isEditionMode);
    }

    private void  configureAndShowEditionFragment(boolean isEditionMode){
        EditionFragment fragment = (EditionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_edition_layout);

        if (fragment == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(EDITION_KEY, isEditionMode);

            //if Edition, send property
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


    // -----------------
    // CALLBACK
    // -----------------

    @Override
    public void onPropertyEdited(Property property) {
        if(isEditionMode){
            Log.e(TAG, "edit");
            updateProperty(property);
            Toast.makeText(this, "Property edited", Toast.LENGTH_LONG).show();
        }else{
            Log.e(TAG, "create");
            createProperty(property);
            Toast.makeText(this, "Property added", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // -----------------
    // RETRIEVE DATA
    // -----------------

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(this);
        this.propertyViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(PropertyViewModel.class);
        this.propertyViewModel.init();
    }

    private void updateProperty(Property property){
        this.propertyViewModel.updateProperty(property);
    }

    private void createProperty(Property property){
        this.propertyViewModel.createProperty(property);
    }
}
