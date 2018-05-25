package com.openclassrooms.realestatemanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String PROPERTY_ID = "PROPERTY_ID";


    public DetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getArguments() != null) {
            long propertyId = getArguments().getLong(PROPERTY_ID, 0);
            Log.e("DetailFragment", "property_id = " + propertyId);
        }else{
            Log.e("DetailFragment", "default property");
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

}
