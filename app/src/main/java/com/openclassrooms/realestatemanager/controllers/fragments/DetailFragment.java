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
    @BindView(R.id.fragment_detail_description) TextView description;
    @BindView(R.id.fragment_detail_specifics) TextView specifics;
    @BindView(R.id.fragment_detail_address) TextView address;
    @BindView(R.id.fragment_detail_photo) ImageView photo;

    public DetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            property = getArguments().getParcelable(Property.PROPERTY_KEY);
            propertyCoordinates = new LatLng(property.getLatitude(), property.getLongitude());
            propertyTitle = property.getStreetNb() + " rue " + property.getStreetName() + ", " + property.getCity();
            this.configureMap(view);
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
        Glide.with(this).load(p.getPhotoUrls().get(0)).into(photo);

        //Description
        description.setText(p.getDescription());

        //Specifics
        String specs = "Surface : " + p.getSurface() + " / Number of rooms : " + p.getNbRooms();
        specifics.setText(specs);

        //Address
        String location = p.getStreetNb() + ", rue " + p.getStreetName() + ", appt " + p.getApptNb()
                + ", " + p.getZipCode() + " " + p.getCity() + ", " + p.getCountry();
        address.setText(location);

    }
}
