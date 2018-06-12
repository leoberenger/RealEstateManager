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
    public static String AREA_KEY = "AREA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        Property property = getIntent().getParcelableExtra(MainActivity.PROPERTY_KEY);
        this.configureAndShowEditionFragment(property);
    }

    private void  configureAndShowEditionFragment(Property property){
        EditionFragment fragment = (EditionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_edition_layout);

        Bundle bundle = new Bundle();
        bundle.putString(AREA_KEY, property.getArea());

        if (fragment == null) {
        fragment = new EditionFragment();
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_edition_fragment, fragment)
                .commit();
        }
    }
}
