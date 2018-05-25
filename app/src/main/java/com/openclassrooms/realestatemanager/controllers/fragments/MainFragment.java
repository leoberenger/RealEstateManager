package com.openclassrooms.realestatemanager.controllers.fragments;


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

    String PROPERTY_ID = "PROPERTY_ID";

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
/*
        long propertyId = getArguments().getLong(PROPERTY_ID, 0);

        if(propertyId == 0){
            Log.e("Main Fragment", "no property pre selected");
        }else{
            Log.e("Main Fragment", "selected property_id = " + propertyId);
        }
*/
        showNearbyProperties();

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

                        long propertyId = adapter.getResult(position).getId();
                        Log.e("MainFragment onItemClik", "clicked property id = " + propertyId);

                        //Replace DetailFragment with new DetailFragment
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();

                        Bundle bundle = new Bundle();
                        bundle.putLong(PROPERTY_ID, propertyId);

                        DetailFragment fragment = new DetailFragment();
                        fragment.setArguments(bundle);
                        ft.replace(R.id.activity_main_detail_fragment, fragment)
                                .commit();
                    }
                });
    }

    // -----------------
    // UPDATE UI
    // -----------------

    private void showNearbyProperties(){
        List<Property> p = propertiesMgr.getRealEstateProperties();
        this.properties.clear();
        this.properties.addAll(p);
        adapter.notifyDataSetChanged();
    }

}
