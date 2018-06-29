package com.openclassrooms.realestatemanager.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static java.security.AccessController.getContext;

/**
 * Created by berenger on 06/03/2018.
 */

public class PropertiesRecyclerAdapter extends RecyclerView.Adapter<PropertiesViewHolder>{

    private final RequestManager glide;

    //FOR DATA
    private final List<Property> properties;
    private SharedPreferences mPreferences;

    //CONSTRUCTOR
    public PropertiesRecyclerAdapter(List<Property> p, RequestManager glide){
        this.properties = p;
        this.glide = glide;
    }

    public Property getResult(int position){
        return this.properties.get(position);
    }

    @NonNull
    @Override
    public PropertiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.properties_recycler_view_item, parent, false);

        this.mPreferences = context.getSharedPreferences("properties", MODE_PRIVATE);

        return new PropertiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertiesViewHolder viewHolder, int position){
        int selectedPosition = mPreferences.getInt("selectedPosition", 0);
        viewHolder.updateWithProperty(this.properties.get(position), position, selectedPosition, this.glide);
    }

    @Override
    public int getItemCount() {
        return this.properties.size();
    }
}
