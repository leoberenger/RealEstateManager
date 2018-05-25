package com.openclassrooms.realestatemanager.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by berenger on 06/03/2018.
 */

public class PropertiesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.properties_recycler_view_item_name) TextView mName;

    public PropertiesViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithProperty(Property property){
        this.mName.setText(property.getName());
    }
}
