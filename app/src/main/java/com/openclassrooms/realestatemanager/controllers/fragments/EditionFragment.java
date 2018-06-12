package com.openclassrooms.realestatemanager.controllers.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.activities.EditionActivity;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.models.SearchQuery;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditionFragment extends Fragment {

    public EditionFragment() {
        // Required empty public constructor
    }

    //FOR CALLBACK
    OnEditionListener mCallback;

    public interface OnEditionListener{
        void onPropertyEdited(Property property);
    }

    //FOR DATA
    private Property property;
    private Property propertyEdited;
    private boolean isEditionMode;

    //FOR DESIGN
    @BindView(R.id.fragment_edition_area) EditText editTextArea;
    @BindView(R.id.edition_button) Button editionButton;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edition, container, false);
        ButterKnife.bind(this, view);

        isEditionMode = getArguments().getBoolean(EditionActivity.EDITION_KEY);

        if(isEditionMode) {
            property = getArguments().getParcelable(EditionActivity.PROPERTY_KEY);
            editTextArea.setText(property.getArea());
        }

        this.configureEditionButton();

        return view;

    }

    private void configureEditionButton(){
        editionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditionMode){
                    property.setArea("Centre ville");
                    propertyEdited = property;
                }else {
                    propertyEdited = new Property(22, "Midtown", 48.380000, -4.480000);
                }

                mCallback.onPropertyEdited(propertyEdited);
            }
        });
    }



    // -------------------------
    // COMMUNICATE WITH ACTIVITY
    // -------------------------

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (EditionFragment.OnEditionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }
    }
}
