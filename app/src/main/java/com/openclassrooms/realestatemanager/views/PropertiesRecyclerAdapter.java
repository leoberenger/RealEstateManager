package com.openclassrooms.realestatemanager.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

import java.util.List;

/**
 * Created by berenger on 06/03/2018.
 */

public class PropertiesRecyclerAdapter extends RecyclerView.Adapter<PropertiesViewHolder>{

    //FOR DATA
    private final List<Property> properties;

    //CONSTRUCTOR
    public PropertiesRecyclerAdapter(List<Property> p){
        this.properties = p;
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

        return new PropertiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertiesViewHolder viewHolder, int position){
        viewHolder.updateWithProperty(this.properties.get(position));
    }

    @Override
    public int getItemCount() {
        return this.properties.size();
    }
}
