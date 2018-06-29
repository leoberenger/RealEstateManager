package com.openclassrooms.realestatemanager.controllers.fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;
import com.openclassrooms.realestatemanager.utils.ItemClickSupport;
import com.openclassrooms.realestatemanager.views.PropertiesRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    String TAG = "Main Fragment";

    //FOR CALLBACK
    OnPropertiesListSelectedListener mCallback;

    public interface OnPropertiesListSelectedListener{
        void onPropertySelected(long propertyId);
    }

    //FOR DATA
    long propertyId = -1;
    private int selectedPosition = -1;
    private SharedPreferences mPreferences;

    // FOR DESIGN
    @BindView(R.id.properties_recycler_view) RecyclerView recyclerView;
    private PropertiesRecyclerAdapter adapter;
    private List<Property> properties;
    private ArrayList<Property> mPropertyArrayList;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.properties_recycler_view, container, false);
        ButterKnife.bind(this, view);

        this.mPreferences = getContext().getSharedPreferences("properties", MODE_PRIVATE);

        this.configureRecyclerView();
        this.configureOnClickRecyclerView();

        mPropertyArrayList = getArguments().getParcelableArrayList("properties");
        if(mPropertyArrayList.size() != 0) {
            this.updatePropertiesList(mPropertyArrayList);
        }
        return view;
    }


    // -----------------
    // CONFIGURATION
    // -----------------

    private void configureRecyclerView(){
        this.properties = new ArrayList<>();
        this.adapter = new PropertiesRecyclerAdapter(this.properties, Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.properties_recycler_view_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        mPreferences.edit().putInt("selectedPosition", position).apply();
                        adapter.notifyDataSetChanged();

                        propertyId = adapter.getResult(position).getId();
                        mCallback.onPropertySelected(propertyId);
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
            mCallback = (OnPropertiesListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }
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
