package com.openclassrooms.realestatemanager.controllers.fragments;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.models.SearchQuery;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment
        implements CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener{

    String TAG = "SearchFragment";

    //FOR DATA
    private String propertyType = "";
    private int [] propertyPOIs = new int[4];
    private final boolean [] poisArray = {false, false, false, false};
    private int dateSelected = 0;
    private int dateSold = 0;
    private int dateCreated = 0;
    private boolean statusSold = false;
    private String [] areas;
    private String priceMin = "";
    private String priceMax = "";
    private String surfaceMin = "";
    private String surfaceMax = "";
    private String nbRooms = "";
    private String nbPhotos = "";
    private SearchQuery query;

    //FOR DESIGN
    @BindView(R.id.checkbox_school) CheckBox checkboxSchool;
    @BindView(R.id.checkbox_metro) CheckBox checkboxMetro;
    @BindView(R.id.checkbox_shopping) CheckBox checkboxShopping;
    @BindView(R.id.checkbox_park) CheckBox checkboxPark;
    @BindView(R.id.search_since) EditText datePicker;
    @BindView(R.id.search_status) RadioGroup radioGroupStatus;
    @BindView(R.id.search_sold) RadioButton radioBtnSold;
    @BindView(R.id.search_not_sold) RadioButton radioBtnNotSold;
    @BindView(R.id.search_type) Spinner spinnerType;
    @BindView(R.id.search_neighborhood) Spinner spinnerAreas;
    @BindView(R.id.search_priceMin) Spinner spinnerPriceMin;
    @BindView(R.id.search_priceMax) Spinner spinnerPriceMax;
    @BindView(R.id.search_surfaceMin) Spinner spinnerSurfaceMin;
    @BindView(R.id.search_surfaceMax) Spinner spinnerSurfaceMax;
    @BindView(R.id.search_nbRooms) Spinner spinnerNbRooms;
    @BindView(R.id.search_nbPhotos) Spinner spinnerNbPhotos;
    @BindView(R.id.search_button) Button searchButton;

    public SearchFragment() {
        // Required empty public constructor
    }

    //FOR CALLBACK
    private OnSearchQueryListener mCallback;

    public interface OnSearchQueryListener{
        void onQuerySelected(SearchQuery query);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        this.configureCheckboxes();
        this.configureDatePicker(datePicker);
        this.configureRadioGroup();
        this.configureAllSpinners();
        this.configureSearch();

        return view;
    }

    private void configureSearch(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Date Picker
                dateSelected = (!datePicker.getText().toString().isEmpty()) ?
                        transformDateFormat(datePicker) :
                        0 ;

                //Checkboxes
                propertyPOIs = checkboxesSelected(poisArray);


                if(statusSold){
                    dateSold = dateSelected;
                    dateCreated = 0;
                }else{
                    dateCreated = dateSelected;
                    dateSold = 0;
                }

                query = new SearchQuery(areas, Long.parseLong(priceMin), Long.parseLong(priceMax),
                        Integer.valueOf(surfaceMin), Integer.valueOf(surfaceMax), Integer.valueOf(nbRooms),
                        Integer.valueOf(nbPhotos), statusSold, dateSelected, dateSold, propertyType, propertyPOIs);


                mCallback.onQuerySelected(query);
            }
        });

    }

    //-----------------------------------
    // DATE PICKERS
    //-----------------------------------

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

    private int transformDateFormat(EditText datePicker){
        //transforms (string) 10/01/2018 to (int) 20180110

        String date = datePicker.getText().toString();
        String orderedDate = date.substring(6,10) + date.substring(3,5) + date.substring(0,2);

        return Integer.valueOf(orderedDate);
    }


    //-----------------------------------
    // CHECK BOXES
    //-----------------------------------

    private void configureCheckboxes(){

        CheckBox [] checkBoxes = {checkboxSchool, checkboxShopping, checkboxPark, checkboxMetro};

        for (CheckBox checkBox : checkBoxes){
            checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
        }

    }

    private int [] checkboxesSelected(boolean[] array){

        int [] propertyPOIs = new int [4];

        for (int i = 0; i<propertyPOIs.length; i++){
            propertyPOIs [i] = (array[i])? 1:0;
        }

        return propertyPOIs;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){
            //POI
            case R.id.checkbox_school: poisArray[0] = isChecked; break;
            case R.id.checkbox_park: poisArray[1] = isChecked; break;
            case R.id.checkbox_shopping: poisArray[2] = isChecked; break;
            case R.id.checkbox_metro: poisArray[3] = isChecked; break;
        }
    }


    //-----------------------------------
    // RADIO GROUP
    //-----------------------------------

    private void configureRadioGroup(){
        radioGroupStatus.setOnCheckedChangeListener(this::onCheckedChanged);
        radioBtnNotSold.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId) {
            case R.id.search_sold:
                statusSold = true;
                break;
            case R.id.search_not_sold:
                statusSold = false;
                break;
        }
    }


    //-----------------------------------
    // SPINNERS
    //-----------------------------------

    private void configureAllSpinners(){
        this.configureSpinner(R.array.search_type, spinnerType);
        this.configureSpinner(R.array.search_neighborhood, spinnerAreas);
        this.configureSpinner(R.array.search_priceMin, spinnerPriceMin);
        this.configureSpinner(R.array.search_priceMax, spinnerPriceMax);
        this.configureSpinner(R.array.search_surfaceMin, spinnerSurfaceMin);
        this.configureSpinner(R.array.search_surfaceMax, spinnerSurfaceMax);
        this.configureSpinner(R.array.search_nbRooms, spinnerNbRooms);
        this.configureSpinner(R.array.search_nbPhotos, spinnerNbPhotos);
    }

    private void configureSpinner(int idRStringArray, Spinner spinner){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                idRStringArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()){
            case R.id.search_type:
                propertyType = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_priceMin:
                priceMin = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_priceMax:
                priceMax = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_surfaceMin:
                surfaceMin = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_surfaceMax:
                surfaceMax = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_nbRooms:
                nbRooms = parent.getItemAtPosition(position).toString();
                break;
            case R.id.search_neighborhood:
                if(position == 0)
                    areas = new String []{"Centre ville", "Port de commerce", "St Martin"};
                else
                    areas = new String []{parent.getItemAtPosition(position).toString(), null, null};
                break;
            case R.id.search_nbPhotos:
                nbPhotos = parent.getItemAtPosition(position).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}


    // -------------------------
    // COMMUNICATE WITH ACTIVITY
    // -------------------------

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (SearchFragment.OnSearchQueryListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }
    }
}
