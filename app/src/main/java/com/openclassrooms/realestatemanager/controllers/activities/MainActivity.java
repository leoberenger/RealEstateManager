package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.DetailFragment;
import com.openclassrooms.realestatemanager.controllers.fragments.MainFragment;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    String PROPERTY_ID = "PROPERTY_ID";
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();

        //this.configureMapsBtn();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Get to SearchManager or Notification Activity
        switch (item.getItemId()){
            case R.id.menu_search:
                Log.e("MainActivity", "Search Btn clicked");
                //Intent intent1 = new Intent(this, SearchActivity.class);
                //startActivity(intent1);
                return true;
            case R.id.menu_map:
                Log.e("MainActivity", "Map Btn clicked");
                Intent intent2 = new Intent(this, MapsActivity.class);
                startActivity(intent2);
                return true;
            case R.id.menu_add:
                Log.e("MainActivity", "Add new Btn clicked");
                return true;
            case R.id.menu_edit:
                Log.e("MainActivity", "Edit Btn clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
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
