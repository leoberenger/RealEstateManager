package com.openclassrooms.realestatemanager.controllers.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.fragments.DetailFragment;
import com.openclassrooms.realestatemanager.controllers.fragments.MainFragment;
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
    public static String PROPERTY_KEY = "property";
    public static String PROPERTIES_KEY = "properties";

    //FOR DATA
    private PropertyViewModel propertyViewModel;
    String PROPERTY_ID = "PROPERTY_ID";
    long propertyId = -1;
    long propertySelectedOnMapId = -1;
    private List<Property> properties;

    //FOR DESIGN
    @BindView(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        this.configureViewModel();
        this.configureToolbar();


        //If from SearchActivity
        if(getIntent().getParcelableExtra("query") != null) {
            SearchQuery query = getIntent().getParcelableExtra("query");
            String area = query.getArea();
            this.getSearchedProperties(area);
        }else {
            this.getAllProperties();
        }
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
                Intent intentCreate = new Intent(this, EditionActivity.class);
                startActivity(intentCreate);
                return true;

            case R.id.menu_edit:
                getPropertyToEdit(propertyId);
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

    private void configureAndShowMainFragment(List<Property> properties){

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.properties_recycler_view);

        Bundle bundle = new Bundle();
        ArrayList<Property> propertyArrayList = new ArrayList<>(properties);
        bundle.putParcelableArrayList(PROPERTIES_KEY, propertyArrayList);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_main_fragment, mainFragment)
                    .commit();
        }

        //If from MapsActivity, property selected
        propertySelectedOnMapId = getIntent().getLongExtra(PROPERTY_ID, -1);

        propertyId = (propertySelectedOnMapId != -1)?
                propertySelectedOnMapId :   //Show property clicked in maps
                properties.get(0).getId();  //Show by default 1st item

        getPropertyToShow(propertyId);
    }

    private void configureAndShowDetailFragment (Property property){

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail_layout);

        Bundle bundle = new Bundle();
        bundle.putParcelable(PROPERTY_KEY, property);

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
        this.propertyViewModel.getAllProperties().observe(this, this::configureAndShowMainFragment);
    }

    private void getSearchedProperties(String area){
        this.propertyViewModel.getSearchedProperties(area).observe(this, this::configureAndShowMainFragment);
    }

    private void getPropertyToShow(long propertyId){
        this.propertyViewModel.getProperty(propertyId).observe(this, this::configureAndShowDetailFragment);
    }

    private void getPropertyToEdit(long propertyId){
        this.propertyViewModel.getProperty(propertyId).observe(this, this::goToEditActivity);
    }


    //-----------------------------------
    // ACTIONS
    //-----------------------------------

    private void goToEditActivity(Property property){
        Intent intentEdit = new Intent(this, EditionActivity.class);
        intentEdit.putExtra(PROPERTY_KEY, property);
        startActivity(intentEdit);
    }

    //-----------------------------------
    // CALLBACKS
    //-----------------------------------

    //From Callback in MainFragment, get Property Selected and show it in DetailFragment
    @Override
    public void onPropertySelected(long propertyId) {
        this.propertyId = propertyId;
        getPropertyToShow(propertyId);
    }

}
