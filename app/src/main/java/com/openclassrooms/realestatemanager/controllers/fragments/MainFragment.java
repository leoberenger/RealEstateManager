package com.openclassrooms.realestatemanager.controllers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.managers.PropertiesMgr;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.utils.ItemClickSupport;
import com.openclassrooms.realestatemanager.views.PropertiesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    // FOR DESIGN
    @BindView(R.id.properties_recycler_view) RecyclerView recyclerView;
    private PropertiesRecyclerAdapter adapter;
    private List<Property> properties;
    private PropertiesMgr propertiesMgr = PropertiesMgr.getInstance();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.properties_recycler_view, container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        showNearbyRestaurants();

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
                        String placeId = String.valueOf(adapter.getResult(position).getId());
                        //Replace DetailFragment with new DetailFragment
                    }
                });
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void showNearbyRestaurants(){
        List<Property> p = propertiesMgr.getRealEstateProperties();
        this.properties.clear();
        this.properties.addAll(p);
        adapter.notifyDataSetChanged();
    }

}
