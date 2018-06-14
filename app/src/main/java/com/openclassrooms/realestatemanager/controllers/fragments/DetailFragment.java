package com.openclassrooms.realestatemanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.activities.MainActivity;
import com.openclassrooms.realestatemanager.models.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    //FOR DATA
    private Property property;

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
            updateShownProperty(property);
        }

        return view;
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
