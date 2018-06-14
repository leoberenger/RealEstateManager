package com.openclassrooms.realestatemanager.controllers.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
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
import okhttp3.OkHttpClient;

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
        bundle.putParcelableArrayList(Property.PROPERTIES_KEY, propertyArrayList);

        if (mainFragment == null) {
            mainFragment = new MainFragment();
            mainFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main_main_fragment, mainFragment)
                    .commit();
        }

        //If from MapsActivity, property selected
        propertySelectedOnMapId = getIntent().getLongExtra(Property.PROPERTY_ID, -1);

        propertyId = (propertySelectedOnMapId != -1)?
                propertySelectedOnMapId :   //Show property clicked in maps
                properties.get(0).getId();  //Show by default 1st item

        getPropertyToShow(propertyId);
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
        getPropertyToShow(propertyId);
    }


    //-----------------------------------
    // PREPOPULATE DATABASE
    //-----------------------------------

    private void createProperties(){

        //-----------------------------------
        //POIS
        //-----------------------------------

        ArrayList<String> p1pois = new ArrayList<String>();
        p1pois.add(Property.poisNames[0]);
        p1pois.add(Property.poisNames[1]);

        ArrayList<String> p2pois = new ArrayList<String>();
        p2pois.add(Property.poisNames[2]);

        ArrayList<String> p3pois = new ArrayList<String>();
        p3pois.add(Property.poisNames[0]);
        p3pois.add(Property.poisNames[3]);
        p3pois.add(Property.poisNames[1]);

        //-----------------------------------
        //PHOTOS
        //-----------------------------------

        ArrayList<String> p1PhotoUrls = new ArrayList<String>();
        p1PhotoUrls.add("https://picsum.photos/200/200/?image=11");
        p1PhotoUrls.add("https://picsum.photos/200/200/?image=104");

        ArrayList<String> p2PhotoUrls = new ArrayList<String>();
        p2PhotoUrls.add("https://picsum.photos/200/200/?image=122");
        p2PhotoUrls.add("https://picsum.photos/200/200/?image=12");
        p2PhotoUrls.add("https://picsum.photos/200/200/?image=23");
        p2PhotoUrls.add("https://picsum.photos/200/200/?image=20");

        ArrayList<String> p3PhotoUrls = new ArrayList<String>();
        p3PhotoUrls.add("https://picsum.photos/200/200/?image=10");
        p3PhotoUrls.add("https://picsum.photos/200/200/?image=102");
        p3PhotoUrls.add("https://picsum.photos/200/200/?image=13");

        ArrayList<String> p1PhotoDescriptions = new ArrayList<String>();
        p1PhotoDescriptions.add("Garden");
        p1PhotoDescriptions.add("Sun");

        ArrayList<String> p2PhotoDescriptions = new ArrayList<String>();
        p2PhotoDescriptions.add("Tower");
        p2PhotoDescriptions.add("Beach");
        p2PhotoDescriptions.add("Kitchen");
        p2PhotoDescriptions.add("Desk");

        ArrayList<String> p3PhotoDescriptions = new ArrayList<String>();
        p3PhotoDescriptions.add("View");
        p3PhotoDescriptions.add("Cake");
        p3PhotoDescriptions.add("Snow");

        //-----------------------------------
        //CREATION OF PROPERTIES
        //-----------------------------------

        Property [] properties = new Property[3];
        for (int i = 0; i<properties.length; i++){
            properties[i] = new Property();
        }

        properties[0] = new Property("Port de commerce", 48.392151, -4.479032,
                95000, 125, 7,
                "Très bel appartement en bord de mer, proche de tous commerces",
                p1PhotoUrls, p1PhotoDescriptions,
                false, 20180201, 0, Property.typesNames[1], 3, p1pois,
                "4", "Glasgow", "4", "29200", "29", "Brest", "France");

        properties[1] = new Property("Centre ville",48.380143,-4.487213,
                250000,215,11,
                "Au centre ville, maison de charme pour grande famille avec jardin",
                p2PhotoUrls, p2PhotoDescriptions,
                false,20180401,0, Property.typesNames[0], 1, p2pois,
                "1", "Danton", "1", "29200", "29", "Brest", "France");

        properties[2] = new Property( "Centre ville", 48.406232, -4.496259,
                328000, 120, 4,
                "Immense loft au coeur du centre ville, parfait pour créateurs de startup",
                p3PhotoUrls, p3PhotoDescriptions,
                true,20180131,20180401, Property.typesNames[3], 2, p3pois,
                "2", "Jaurès", "0", "29200", "29", "Brest", "France");

        for(int i = 0; i<properties.length;i++) {
            this.propertyViewModel.createProperty(properties[i]);
        }
    }

}
