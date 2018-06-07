package com.openclassrooms.realestatemanager.controllers.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    String TAG = "DetailFragment";

    //FOR DATA
    private PropertyViewModel propertyViewModel;

    //FOR DESIGN
    @BindView(R.id.fragment_detail_textview)
    TextView textView;

    String PROPERTY_ID = "PROPERTY_ID";

    public DetailFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        this.configureViewModel();

        long propertyId = getArguments().getLong(PROPERTY_ID, 0);

        if(propertyId != -1) {
            //Retrieve Property with its ID
            this.getProperty(propertyId);
        }else{
            textView.setText("No Property Selected");
        }

        return view;
    }

    // -----------------
    // RETRIEVE DATA
    // -----------------

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.propertyViewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(PropertyViewModel.class);
        this.propertyViewModel.init();
    }

    private void getProperty(long propertyId){
        this.propertyViewModel.getProperty(propertyId).observe(this, this::updateShownProperty);
    }


    // -----------------
    // UPDATE UI
    // -----------------

    private void updateShownProperty(Property property){
        textView.setText(property.getArea());
    }
}
