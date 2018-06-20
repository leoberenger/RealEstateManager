package com.openclassrooms.realestatemanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements OnMapReadyCallback {

    String TAG = "DetailFragment";

    //FOR DATA
    private Property property;

    //FOR MAP
    private LatLng propertyCoordinates;
    private String propertyTitle;
    private GoogleMap mMap;
    MapView mapView;

    //FOR DESIGN
    @BindView(R.id.fragment_detail_description) TextView textViewDescription;
    @BindView(R.id.fragment_detail_surface) TextView textViewSurface;
    @BindView(R.id.fragment_detail_nbRooms) TextView textViewNbRooms;
    @BindView(R.id.fragment_detail_pois) TextView textViewPOIs;
    @BindView(R.id.fragment_detail_address_street) TextView textViewAddressStreet;
    @BindView(R.id.fragment_detail_address_appt) TextView textViewAddressAppt;
    @BindView(R.id.fragment_detail_address_city) TextView textViewAddressCity;
    @BindView(R.id.fragment_detail_address_country) TextView textViewAddressCountry;
    @BindView(R.id.fragment_detail_photo) ImageView photo;
    @BindView(R.id.fragment_detail_photo_description) TextView textViewPhotoDescription;

    public DetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            property = getArguments().getParcelable(Property.PROPERTY_KEY);
            propertyCoordinates = new LatLng(property.getLatitude(), property.getLongitude());
            if(propertyCoordinates.latitude != 0 && propertyCoordinates.longitude !=0){
                propertyTitle = property.getStreetNb() + " rue " + property.getStreetName() + ", " + property.getCity();
                this.configureMap(view);
            }
            updateShownProperty(property);
        }

        return view;
    }


    //-----------------------------------
    // MAP CONFIGURATION
    //-----------------------------------

    private void configureMap(View v){
        mapView = v.findViewById(R.id.map);
        if (mapView != null) {
            // Initialise the MapView
            mapView.onCreate(null);
            // Set the map ready callback to receive the GoogleMap object
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        // Center camera on marker

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(propertyCoordinates, 20f));

        mMap.addMarker(new MarkerOptions()
            .position(propertyCoordinates)
            .title(propertyTitle))
            .showInfoWindow();
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void updateShownProperty(Property p){

        //Img
        if(!p.getPhotoUrl().isEmpty()) {
            Glide.with(this).load(p.getPhotoUrl()).into(photo);
            textViewPhotoDescription.setText(p.getPhotoDescription());
        }else
            textViewPhotoDescription.setText("No photo");

        //Description
        if(!p.getDescription().isEmpty()){
            textViewDescription.setText(p.getDescription());
        }

        //Specifics
        String surface = (p.getSurface() != 0)?
                String.valueOf(p.getSurface()) + "m²":
                "No information";
        textViewSurface.setText(surface);

        String nbRooms = (p.getNbRooms() != 0)?
                String.valueOf(p.getNbRooms()):
                "No information";
        textViewNbRooms.setText(nbRooms);

        String school = (p.getPoiSchool()==1)?"School":"";
        String park = (p.getPoiPark()==1)?"Park":"";
        String shopping = (p.getPoiShopping()==1)?"Shopping":"";
        String metro = (p.getPoiMetro()==1)?"Metro":"";

        String pois = (!((school.isEmpty()) && (park.isEmpty()) && (shopping.isEmpty()) && (metro.isEmpty())))?
                school + " " + park + " " + shopping + " " + metro:
                "No information";
        textViewPOIs.setText(pois);

        //Address
        String street = (!(p.getStreetNb().isEmpty()) && (p.getStreetName().isEmpty()))?
                p.getStreetNb() + ", rue " + p.getStreetName():
                "No information";
        String city = (!(p.getZipCode().isEmpty()) && (p.getCity().isEmpty()))?
                p.getZipCode() + " " + p.getCity():
                "";
        String appt = (!p.getApptNb().isEmpty())?
                "Appt " + p.getApptNb():
                "";
        String country = (!p.getCountry().isEmpty())?
                            p.getCountry():
                            "";
        textViewAddressStreet.setText(street);
        textViewAddressAppt.setText(appt);
        textViewAddressCity.setText(city);
        textViewAddressCountry.setText(country);
    }
}
