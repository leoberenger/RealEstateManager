package com.openclassrooms.realestatemanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.models.Property;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.fragment_detail_textview)
    TextView textView;

    String PROPERTY_ID = "PROPERTY_ID";
    PropertiesMgr propertiesMgr = PropertiesMgr.getInstance();

    public DetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        if(getArguments() != null) {
            //Retrieve Property ID
            long propertyId = getArguments().getLong(PROPERTY_ID, 0);

            //Retrieve Property with its ID
            Property property = propertiesMgr.getProperty(propertyId);

            //Set Data
            textView.setText(property.getName());

        }else{
            textView.setText("No Property Selected");
        }

        return view;
    }
}
