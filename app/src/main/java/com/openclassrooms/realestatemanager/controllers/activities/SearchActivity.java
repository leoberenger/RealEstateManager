package com.openclassrooms.realestatemanager.controllers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.SearchFragment;
import com.openclassrooms.realestatemanager.models.SearchQuery;

public class SearchActivity extends AppCompatActivity
    implements SearchFragment.OnSearchQueryListener{

    String TAG = "SearchActivity";

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

    //-----------------------------------
    // FRAGMENT CALLBACK
    //-----------------------------------

    @Override
    public void onQuerySelected(SearchQuery query) {
        Intent intentSearch = new Intent(this, MainActivity.class);
        intentSearch.putExtra("query", query);
        startActivity(intentSearch);
    }
}
