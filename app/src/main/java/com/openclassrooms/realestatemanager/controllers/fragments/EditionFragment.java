package com.openclassrooms.realestatemanager.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.activities.EditionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditionFragment extends Fragment {

    public EditionFragment() {
        // Required empty public constructor
    }

    //FOR DESIGN
    @BindView(R.id.fragment_edition_area) EditText editTextArea;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edition, container, false);
        ButterKnife.bind(this, view);

        String area = getArguments().getString(EditionActivity.AREA_KEY);
        editTextArea.setText(area);

        return view;

    }

}
