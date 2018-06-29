package com.openclassrooms.realestatemanager.controllers.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.DetailFragment;
import com.openclassrooms.realestatemanager.controllers.fragments.MainFragment;
import com.openclassrooms.realestatemanager.database.DataGenerator;
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.models.SearchQuery;
import com.openclassrooms.realestatemanager.utils.Utils;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements MainFragment.OnPropertiesListSelectedListener {

    String TAG = "MainActivity";

    //FOR DATA
    private PropertyViewModel propertyViewModel;
    long propertyId = -1;
    long propertySelectedOnMapId = -1;

    //FOR DESIGN
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureToolbar();

        this.configureViewModel();
        //this.createProperties();
        Stetho.initializeWithDefaults(this);

        //If from SearchActivity
        if(getIntent().getParcelableExtra(SearchQuery.SEARCH_QUERY_KEY) != null) {
            SearchQuery query = getIntent().getParcelableExtra(SearchQuery.SEARCH_QUERY_KEY);
            this.getSearchedProperties(query);
        }else {
            this.getAllProperties();
        }

        //If from MapsActivity
        propertySelectedOnMapId = getIntent().getLongExtra(Property.PROPERTY_ID, -1);

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
                Intent intentCreate = new Intent(this, EditionActivity.class);
                startActivity(intentCreate);
                return true;

            case R.id.menu_edit:
                getPropertyToEdit(propertyId);
                return true;

            case R.id.menu_calculator:
                Intent intentCalculator = new Intent(this, CalculatorActivity.class);
                startActivity(intentCalculator);
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

    private void showMainFragment(ArrayList<Property> propertyArrayList){
        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.properties_recycler_view);

        Bundle bundle = new Bundle();

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            bundle.putParcelableArrayList(Property.PROPERTIES_KEY, propertyArrayList);
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_main_fragment, mainFragment)
                    .commit();
        }
    }

    private void configureMainFragment(List<Property> properties){

        if (properties.size() == 0){
            Toast.makeText(this, "No property corresponding", Toast.LENGTH_LONG).show();

        //If from Maps Activity, show selected property
        }else if (propertySelectedOnMapId != -1) {
            ArrayList<Property> propertyArrayList = showPropertySelectedOnMap(properties);
            showMainFragment(propertyArrayList);

            getPropertyToShowInDetail(propertyId);

        //Else show all properties
        }else {
            ArrayList<Property> propertyArrayList = showAllProperties(properties);
            showMainFragment(propertyArrayList);

            getPropertyToShowInDetail(propertyId);
        }
    }

    private ArrayList<Property> showPropertySelectedOnMap(List<Property> properties){
        ArrayList<Property> propertyArrayList = new ArrayList<>();

        for(int i = 0; i<properties.size(); i++){
            if(properties.get(i).getId() == propertySelectedOnMapId){
                propertyId = propertySelectedOnMapId;
                propertyArrayList = new ArrayList<Property>();
                propertyArrayList.add(properties.get(i));
            }
        }
        return propertyArrayList;
    }

    private ArrayList<Property> showAllProperties(List<Property> properties){
        ArrayList<Property> propertyArrayList = new ArrayList<>(properties);

        //Show by default 1st item in details
        propertyId = properties.get(0).getId();

        return propertyArrayList;
    }

    private void configureAndShowDetailFragment (Property property){

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_detail_layout);

        Bundle bundle = new Bundle();
        bundle.putParcelable(Property.PROPERTY_KEY, property);

        if (fragment == null) {
            fragment = new DetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_detail_fragment, fragment)
                    .addToBackStack(null)
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
        this.propertyViewModel.getAllProperties().observe(this, this::configureMainFragment);
    }

    private void getSearchedProperties(SearchQuery query){
        this.propertyViewModel.getSearchedProperties(
                query.getAreas(), query.getPriceMin(),
                query.getPriceMax(), query.getSurfaceMin(), query.getSurfaceMax(),
                query.getNbRooms(), query.getNbPhotos(), query.isSold(), query.getDateCreated(), query.getDateSold(),
                query.getPropertyType(), query.getPropertyPOIs()[0], query.getPropertyPOIs()[1],
                query.getPropertyPOIs()[2], query.getPropertyPOIs()[3]
                )
            .observe(this, this::configureMainFragment);
    }

    private void getPropertyToShowInDetail(long propertyId){
        this.propertyViewModel.getProperty(propertyId).observe(this, this::configureAndShowDetailFragment);
    }

    private void getPropertyToEdit(long propertyId){
        this.propertyViewModel.getProperty(propertyId).observe(this, new Observer<Property>() {
            @Override
            public void onChanged(@Nullable Property property) {
                Intent intentEdit = new Intent(getApplicationContext(), EditionActivity.class);
                intentEdit.putExtra(Property.PROPERTY_KEY, property);

                startActivity(intentEdit);
            }
        });

    }


    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    //From Callback in MainFragment, get Property Selected and show it in DetailFragment
    @Override
    public void onPropertySelected(long propertyId) {
        this.propertyId = propertyId;
        getPropertyToShowInDetail(propertyId);
    }


    //-----------------------------------
    // PREPOPULATE DATABASE
    //-----------------------------------

    private void createProperties(){

        Property [] properties = DataGenerator.generateProperties();

        for(int i = 0; i<properties.length;i++) {
            this.propertyViewModel.createProperty(properties[i]);
        }
    }

}
