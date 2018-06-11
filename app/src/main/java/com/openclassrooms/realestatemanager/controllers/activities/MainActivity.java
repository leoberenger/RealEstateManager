package com.openclassrooms.realestatemanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
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
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.models.SearchQuery;
import com.openclassrooms.realestatemanager.utils.Utils;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnPropertiesListSelectedListener {

    String TAG = "MainActivity";

    //FOR DATA
    private PropertyViewModel propertyViewModel;
    String PROPERTY_ID = "PROPERTY_ID";
    long propertyId = -1;
    private List<Property> properties;

    //FOR DESIGN
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.configureViewModel();
        this.getAllProperties();

        Intent intent = getIntent();
        propertyId = intent.getLongExtra(PROPERTY_ID, -1);

        if(intent.getParcelableExtra("query") != null) {
            SearchQuery query = (SearchQuery) intent.getParcelableExtra("query");
            Log.e(TAG, "area selected = " + query.getArea());
        }

        this.configureAndShowMainFragment(propertyId);
        this.configureAndShowDetailFragment(propertyId);
    }

    //-----------------------------------
    // NAVIGATION
    //-----------------------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.menu_search:
                Log.e("MainActivity", "Search Btn clicked");
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                return true;

            case R.id.menu_map:
                if(Utils.isInternetAvailable(getApplicationContext())){
                    Intent intentMap = new Intent(this, MapsActivity.class);
                    startActivity(intentMap);
                }else{
                    Toast.makeText(getApplicationContext(), "No Internet Connexion Available", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.menu_add:
                Log.e("MainActivity", "Add new Btn clicked");
                return true;

            case R.id.menu_edit:
                Log.e("MainActivity", "Edit Btn clicked");
                if(propertyId != -1) {
                    Intent intentEdit = new Intent(this, EditionActivity.class);
                    intentEdit.putExtra(PROPERTY_ID, propertyId);
                    startActivity(intentEdit);
                }else{
                    Toast.makeText(this, "No Propriety selected", Toast.LENGTH_LONG).show();
                }
                return true;

            case R.id.menu_calculator:
                Log.e("MainActivity", "Calculator Btn clicked");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //-----------------------------------
    // CONFIGURATION
    //-----------------------------------

    private void configureToolbar(){
        setSupportActionBar(mToolbar);
    }

    private void configureAndShowMainFragment(long propertyId){

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.properties_recycler_view);

        Bundle bundle = new Bundle();
        bundle.putLong(PROPERTY_ID, propertyId);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_main_fragment, mainFragment)
                    .commit();
        }
    }

    private void configureAndShowDetailFragment (long propertyId){

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail_layout);

        Bundle bundle = new Bundle();
        bundle.putLong(PROPERTY_ID, propertyId);

        if (fragment == null) {
            fragment = new DetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_detail_fragment, fragment)
                    .commit();
        }
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

    private void getAllProperties(){
        this.propertyViewModel.getAllProperties().observe(this, this::updatePropertiesList);
    }

    private void updatePropertiesList(List<Property> properties){
        this.properties = properties;
        Log.e(TAG, "area 0 = " + properties.get(0).getArea());
    }

    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    //From Callback in MainFragment, get Property Selected and show it in DetailFragment
    @Override
    public void onPropertySelected(long propertyId) {
        configureAndShowDetailFragment(propertyId);
    }

}
