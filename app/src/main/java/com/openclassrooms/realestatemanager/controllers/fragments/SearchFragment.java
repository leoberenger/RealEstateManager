package com.openclassrooms.realestatemanager.controllers.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.openclassrooms.realestatemanager.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment
        implements CompoundButton.OnCheckedChangeListener {

    String TAG = "SearchFragment";

    //FOR DATA
    String types = "";
    String poi = "";
    final boolean [] typesArray = {false, false, false, false};
    final boolean [] poiArray = {false, false, false, false};

    //FOR DESIGN
    @BindView(R.id.search_since) EditText datePicker;
    @BindView(R.id.search_button) Button searchButton;
    @BindView(R.id.checkbox_house) CheckBox checkboxHouse;
    @BindView(R.id.checkbox_apartment) CheckBox checkboxApartment;
    @BindView(R.id.checkbox_duplex) CheckBox checkboxDuplex;
    @BindView(R.id.checkbox_penthouse) CheckBox checkboxPenthouse;
    @BindView(R.id.checkbox_school) CheckBox checkboxSchool;
    @BindView(R.id.checkbox_metro) CheckBox checkboxMetro;
    @BindView(R.id.checkbox_shopping) CheckBox checkboxShopping;
    @BindView(R.id.checkbox_park) CheckBox checkboxPark;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        this.configureCheckboxes();
        this.configureDatePicker(datePicker);
        this.configureSearch();

        return view;
    }

    private void configureSearch(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] typeNames = {"House", "Apartment", "Duplex", "Penthouse"};
                String [] poiNames = {"School", "Park", "Shopping", "Metro"};
                types = checkboxesSelected(typesArray, typeNames);
                Log.e(TAG, types);
                poi = checkboxesSelected(poiArray, poiNames);
                Log.e(TAG, poi);
            }
        });

    }

    private void configureDatePicker(final EditText datePicker){

        // perform click event on edit text
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

                // date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String day, month;
                                if(dayOfMonth<10) day = "0"+dayOfMonth;
                                else day = ""+dayOfMonth;

                                if(monthOfYear<9) month = "0"+(monthOfYear+1);
                                else month = ""+(monthOfYear+1);

                                String dateString = day + "/" + month + "/" + year;
                                datePicker.setText(dateString);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void configureCheckboxes(){
        //TYPES
        checkboxHouse.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxApartment.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxDuplex.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxPenthouse.setOnCheckedChangeListener(this::onCheckedChanged);

        //POI
        checkboxSchool.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxShopping.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxPark.setOnCheckedChangeListener(this::onCheckedChanged);
        checkboxMetro.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    //Return the types selected
    public String checkboxesSelected(boolean [] array, String [] checkboxesNames){

        StringBuilder str = new StringBuilder("Checkboxes selected : ");

        for(int i = 0; i < array.length; i++) {
            if (array[i]) {
                String type = " " + checkboxesNames[i] + " ";
                str.append(type);
            }
        }

        return str.toString();
    }

    //Check if all types are set to false
    public boolean noDeskSelected(boolean [] desks){
        boolean noDeskSelected = true;

        for(boolean desk:desks) {
            if(desk) noDeskSelected = false;
        }

        return noDeskSelected;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){
            //Types
            case R.id.checkbox_house: typesArray[0] = isChecked; break;
            case R.id.checkbox_apartment: typesArray[1] = isChecked; break;
            case R.id.checkbox_duplex: typesArray[2] = isChecked; break;
            case R.id.checkbox_penthouse: typesArray[3] = isChecked; break;

            //POI
            case R.id.checkbox_school: poiArray[0] = isChecked; break;
            case R.id.checkbox_park: poiArray[1] = isChecked; break;
            case R.id.checkbox_shopping: poiArray[2] = isChecked; break;
            case R.id.checkbox_metro: poiArray[3] = isChecked; break;
        }
    }
}