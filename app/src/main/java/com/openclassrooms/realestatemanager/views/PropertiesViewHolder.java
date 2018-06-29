package com.openclassrooms.realestatemanager.views;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.Property;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by berenger on 06/03/2018.
 */

public class PropertiesViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.properties_recycler_view_item_layout) LinearLayout itemLayout;
    @BindView(R.id.properties_recycler_view_item_area) TextView itemArea;
    @BindView(R.id.properties_recycler_view_item_type) TextView itemType;
    @BindView(R.id.properties_recycler_view_item_price) TextView itemPrice;
    @BindView(R.id.properties_recycler_view_item_img) ImageView itemImg;


    public PropertiesViewHolder(View itemView){
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithProperty(Property property, int currentPosition, int selectedPosition, RequestManager glide){

        if(selectedPosition == currentPosition){
            this.itemLayout.setBackgroundColor(Color.parseColor("#FF4081"));
            this.itemPrice.setTextColor(Color.WHITE);
        }else
            this.itemLayout.setBackgroundColor(Color.WHITE);

        //Type
        this.itemType.setText(property.getType());

        //Area
        this.itemArea.setText(property.getArea());

        //Price
        String price = "$" + property.getPrice();
        this.itemPrice.setText(price);

        //Img
        glide.load(property.getPhotoUrl()).into(itemImg);
    }
}
