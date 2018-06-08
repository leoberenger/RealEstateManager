package com.openclassrooms.realestatemanager.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.MainFragment;
import com.openclassrooms.realestatemanager.controllers.fragments.SearchFragment;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.configureAndShowSearchFragment();
    }

    //-----------------------------------
    // CONFIGURATION
    //-----------------------------------

    private void configureAndShowSearchFragment(){

        SearchFragment fragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_search_root_layout);

        if (fragment == null) {
            fragment = new SearchFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_fragment_layout, fragment)
                    .commit();
        }
    }
}
