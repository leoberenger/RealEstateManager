package com.openclassrooms.realestatemanager.controllers.fragments;


import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.utils.ItemClickSupport;
import com.openclassrooms.realestatemanager.views.PropertiesRecyclerAdapter;
import com.openclassrooms.realestatemanager.views.PropertyViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    OnPropertiesListSelectedListener mCallback;

    public interface OnPropertiesListSelectedListener{
        public void onPropertySelected(long propertyId);
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (OnPropertiesListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }


    }

    //FOR DATA
    private PropertyViewModel propertyViewModel;

    String PROPERTY_ID = "PROPERTY_ID";
    long propertyId = -1;

    // FOR DESIGN
    @BindView(R.id.properties_recycler_view) RecyclerView recyclerView;
    private PropertiesRecyclerAdapter adapter;
    private List<Property> properties;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.properties_recycler_view, container, false);
        ButterKnife.bind(this, view);

        this.configureViewModel();
        this.getAllProperties();

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        return view;
    }


    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureRecyclerView(){
        this.properties = new ArrayList<>();
        this.adapter = new PropertiesRecyclerAdapter(this.properties);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.properties_recycler_view_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                        propertyId = adapter.getResult(position).getId();
                        Log.e("MainFragment onItemClik", "clicked property id = " + propertyId);

                        mCallback.onPropertySelected(propertyId);
                    }
                });
    }

    private void configureViewModel(){
        ViewModelFactory viewModelFactory = Injection.provideViewModelFactory(getContext());
        this.propertyViewModel = ViewModelProviders.of(getActivity(), viewModelFactory)
                .get(PropertyViewModel.class);
        this.propertyViewModel.init();
    }

    private void getAllProperties(){
        this.propertyViewModel.getAllProperties().observe(this, this::updatePropertiesList);
    }



    // -----------------
    // UPDATE UI
    // -----------------


    private void updatePropertiesList(List<Property> properties){
        this.properties.clear();
        this.properties.addAll(properties);
        adapter.notifyDataSetChanged();
    }
}
