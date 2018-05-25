package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.DetailFragment;
import com.openclassrooms.realestatemanager.controllers.fragments.MainFragment;
import com.openclassrooms.realestatemanager.utils.Utils;

public class MainActivity extends AppCompatActivity {

    String PROPERTY_ID = "PROPERTY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.configureMapsBtn();

    }
/*
    private void configureMapsBtn(){
        Button mapsBtn = (Button) findViewById(R.id.activity_main_maps_btn);
        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity", "OnClick Maps Btn");
                if(Utils.isInternetAvailable(getApplicationContext())){
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "No Internet Connexion Available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void configureAndShowMainFragment(){

        Intent intent = getIntent();
        long propertyId = intent.getLongExtra(PROPERTY_ID, 0);

        Bundle bundle = new Bundle();
        bundle.putLong(PROPERTY_ID, propertyId);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.properties_recycler_view);

        if (mainFragment == null) {

            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, mainFragment)
                    .commit();
        }
    }

    private void configureAndShowDetailFragment(){

        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail_layout);

        if (detailFragment == null) {

            detailFragment = new DetailFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_frame_layout, detailFragment)
                    .commit();
        }
    }
*/
}
