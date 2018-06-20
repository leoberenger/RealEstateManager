package com.openclassrooms.realestatemanager.controllers.fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.controllers.activities.EditionActivity;
import com.openclassrooms.realestatemanager.models.Property;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditionFragment extends Fragment
        implements CompoundButton.OnCheckedChangeListener,
        RadioGroup.OnCheckedChangeListener{

    String TAG = "EditionFragment";

    public EditionFragment() {
        // Required empty public constructor
    }

    //FOR CALLBACK
    OnEditionListener mCallback;

    public interface OnEditionListener{
        void onPropertyEdited(Property property);
    }

    //FOR DATA
    private Property property;
    private Property propertyEdited;
    private boolean isEditionMode;
    private boolean [] poisArray = {false, false, false, false};
    private int statusSold;
    private int dateSold;

    String area = "";
    long price = 0;
    int surface = 0;
    int nbRooms = 0;
    String description = "";

    String photoUrl = "";
    String photoDescription = "";
    int nbPhotos = 0;

    int isSold = 0;
    int dateOfSelling = 0;
    String type = "";
    int agentID = 0;

    int poiSchool = 0;
    int poiPark = 0;
    int poiShopping= 0;
    int poiMetro= 0;

    Double latitude = 0d;
    Double longitude = 0d;

    String streetNb = "";
    String streetName = "";
    String apptNb = "";
    String zipCode = "";
    String stateNb = "";
    String city = "";
    String country = "";

    //STATIC DATA FOR PICTURE
    private static final String PERMS = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    private Uri uriImageSelected = null;

    //FOR DESIGN
    @BindView(R.id.edition_image_preview) ImageView imageViewPreview;
    @BindView(R.id.fragment_edition_area) EditText editTextArea;
    @BindView(R.id.fragment_edition_price) EditText editTextPrice;
    @BindView(R.id.fragment_edition_surface) EditText editTextSurface;
    @BindView(R.id.fragment_edition_type) EditText editTextType;
    @BindView(R.id.fragment_edition_nbRooms) EditText editTextNbRooms;
    @BindView(R.id.fragment_edition_description) EditText editTextDescription;
    @BindView(R.id.fragment_edition_agentID) EditText editTextAgentID;
    @BindView(R.id.edition_checkbox_school) CheckBox checkboxSchool;
    @BindView(R.id.edition_checkbox_metro) CheckBox checkboxMetro;
    @BindView(R.id.edition_checkbox_shopping) CheckBox checkboxShopping;
    @BindView(R.id.edition_checkbox_park) CheckBox checkboxPark;
    @BindView(R.id.edition_status) RadioGroup radioGroupStatus;
    @BindView(R.id.edition_sold) RadioButton radioBtnSold;
    @BindView(R.id.edition_not_sold) RadioButton radioBtnNotSold;
    @BindView(R.id.edition_button) Button editionButton;
    @BindView(R.id.edition_photo_description) EditText editTextPhotoDescription;
    @BindView(R.id.edition_address_streetNb) EditText editTextStreetNb;
    @BindView(R.id.edition_address_streetName) EditText editTextStreetName;
    @BindView(R.id.edition_address_zipCode) EditText editTextZipCode;
    @BindView(R.id.edition_address_apptNb) EditText editTextApptNb;
    @BindView(R.id.edition_address_state) EditText editTextState;
    @BindView(R.id.edition_address_city) EditText editTextCity;
    @BindView(R.id.edition_address_country) EditText editTextCountry;
    @BindView(R.id.edition_address_latitude) EditText editTextLatitude;
    @BindView(R.id.edition_address_longitude) EditText editTextLongitude;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edition, container, false);
        ButterKnife.bind(this, view);

        isEditionMode = getArguments().getBoolean(EditionActivity.EDITION_KEY);

        if(isEditionMode) {
            property = getArguments().getParcelable(EditionActivity.PROPERTY_KEY);
            this.showPropertyCurrentValues(property);
        }

        this.configureRadioGroup();
        this.configureCheckboxes();
        this.configureEditionButton();

        return view;

    }

    private void configureEditionButton(){
        editionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getDataFromUserInput();

                if(isEditionMode){

                    property.setArea(area);
                    property.setPrice(price);
                    property.setSurface(surface);
                    property.setNbRooms(nbRooms);
                    property.setDescription(description);

                    property.setType(type);
                    property.setPhotoUrl(photoUrl);
                    property.setPhotoDescription(photoDescription);
                    property.setNbPhotos(nbPhotos);

                    property.setIsSold(isSold);
                    property.setDateSold(dateOfSelling);
                    property.setAgentID(agentID);

                    property.setPoiSchool(poiSchool);
                    property.setPoiPark(poiPark);
                    property.setPoiShopping(poiShopping);
                    property.setPoiMetro(poiMetro);

                    property.setLatitude(latitude);
                    property.setLongitude(longitude);

                    property.setStreetNb(streetNb);
                    property.setStreetName(streetName);
                    property.setApptNb(apptNb);
                    property.setZipCode(zipCode);
                    property.setStateNb(stateNb);
                    property.setCity(city);
                    property.setCountry(country);

                    propertyEdited = property;

                }else {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(Calendar.getInstance().getTime());
                    int dateCreated = Integer.valueOf(timeStamp);

                    propertyEdited = new Property(area, latitude, longitude, price, surface,
                            nbRooms, description, photoUrl, photoDescription, nbPhotos, isSold,
                            dateCreated, dateOfSelling, type, agentID, poiSchool, poiPark, poiShopping, poiMetro,
                            streetNb, streetName, apptNb, zipCode, stateNb, city, country
                            );
                }

                mCallback.onPropertyEdited(propertyEdited);
            }
        });
    }

    private void showPropertyCurrentValues(Property p){
        editTextArea.setText(p.getArea());
        editTextPrice.setText(String.valueOf(p.getPrice()));
        editTextSurface.setText(String.valueOf(p.getSurface()));
        editTextType.setText(p.getType());
        editTextAgentID.setText(String.valueOf(p.getAgentID()));
        editTextNbRooms.setText(String.valueOf(p.getNbRooms()));
        editTextDescription.setText(p.getDescription());

        Glide.with(this)
                .load(p.getPhotoUrl())
                .into(this.imageViewPreview);
        uriImageSelected = Uri.parse(p.getPhotoUrl());

        editTextPhotoDescription.setText(p.getPhotoDescription());

        if(p.isSold() == 1){ radioBtnSold.setChecked(true);
        }else{ radioBtnNotSold.setChecked(true); }

        if(p.getPoiSchool() != 0) {
            checkboxSchool.setChecked(true);
            poisArray[0] = true;
        }
        if(p.getPoiPark() != 0){
            checkboxPark.setChecked(true);
            poisArray[1] = true;
        }
        if(p.getPoiShopping() != 0) {
            checkboxShopping.setChecked(true);
            poisArray[2] = true;
        }
        if(p.getPoiMetro() != 0) {
            checkboxMetro.setChecked(true);
            poisArray[3] = true;
        }

        editTextStreetNb.setText(p.getStreetNb());
        editTextStreetName.setText(p.getStreetName());
        editTextApptNb.setText(p.getApptNb());
        editTextZipCode.setText(p.getZipCode());
        editTextState.setText(p.getStateNb());
        editTextCity.setText(p.getCity());
        editTextCountry.setText(p.getCountry());

        editTextLatitude.setText(String.valueOf(p.getLatitude()));
        editTextLongitude.setText(String.valueOf(p.getLongitude()));
    }

    private void getDataFromUserInput(){
        area = (editTextArea.getText().toString());
        price = (!editTextPrice.getText().toString().isEmpty())?
                (Long.valueOf(editTextPrice.getText().toString()))
                :0L;
        surface = (!editTextSurface.getText().toString().isEmpty())?
                Integer.valueOf(editTextSurface.getText().toString()):
                0;
        nbRooms = (!editTextNbRooms.getText().toString().isEmpty())?
                Integer.valueOf(editTextNbRooms.getText().toString()):
                0;
        description = editTextDescription.getText().toString();

        photoUrl = (uriImageSelected !=null)?
                uriImageSelected.toString():
                "";
        photoDescription = editTextPhotoDescription.getText().toString();
        nbPhotos = 1;

        isSold = statusSold;
        dateOfSelling = dateSold;
        type = editTextType.getText().toString();
        agentID = (!editTextAgentID.getText().toString().isEmpty())?
                Integer.valueOf(editTextAgentID.getText().toString()):
                0;

        poiSchool = (poisArray[0])?1:0;
        poiPark = (poisArray[1])?1:0;
        poiShopping= (poisArray[2])?1:0;
        poiMetro= (poisArray[3])?1:0;

        latitude = (!editTextLatitude.getText().toString().isEmpty())?
                Double.valueOf(editTextLatitude.getText().toString()):
                0;
        longitude = (!editTextLongitude.getText().toString().isEmpty())?
                Double.valueOf(editTextLongitude.getText().toString()):
                0;

        streetNb = editTextStreetNb.getText().toString();
        streetName = editTextStreetName.getText().toString();
        apptNb = editTextApptNb.getText().toString();
        zipCode = editTextZipCode.getText().toString();
        stateNb = editTextState.getText().toString();
        city = editTextCity.getText().toString();
        country = editTextCountry.getText().toString();
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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()){
            case R.id.edition_checkbox_school: poisArray[0] = isChecked; break;
            case R.id.edition_checkbox_park: poisArray[1] = isChecked; break;
            case R.id.edition_checkbox_shopping: poisArray[2] = isChecked; break;
            case R.id.edition_checkbox_metro: poisArray[3] = isChecked; break;
        }
    }


    //-----------------------------------
    // RADIO GROUP
    //-----------------------------------

    private void configureRadioGroup(){
        radioGroupStatus.setOnCheckedChangeListener(this::onCheckedChanged);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId) {
            case R.id.edition_sold:
                statusSold = 1;
                String timeStamp = new SimpleDateFormat("yyyyMMdd", Locale.FRANCE).format(Calendar.getInstance().getTime());
                dateSold = Integer.valueOf(timeStamp);
                break;

            case R.id.edition_not_sold:
                statusSold = 0;
                dateSold = 0;
                Log.e(TAG, "oncheckedchanged : sold?" + statusSold);
                break;
        }
    }

    // --------------------------
    // RETRIEVE PHOTO FROM PHONE
    // --------------------------

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @OnClick(R.id.edition_add_photo_button)
    // 3 - Ask permission when accessing to this listener
    @AfterPermissionGranted(RC_IMAGE_PERMS)
    public void onClickAddFile() {
        this.chooseImageFromPhone();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 6 - Calling the appropriate method after activity result
        this.handleResponse(requestCode, resultCode, data);
    }

    // --------------------
    // FILE MANAGEMENT
    // --------------------

    private void chooseImageFromPhone(){
        if (!EasyPermissions.hasPermissions(getContext(), PERMS)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_IMAGE_PERMS, PERMS);
            return;
        }
        // 3 - Launch an "Selection Image" Activity
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RC_CHOOSE_PHOTO);
    }

    // 4 - Handle activity response (after user has chosen or not a picture)
    private void handleResponse(int requestCode, int resultCode, Intent data){
        if (requestCode == RC_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) { //SUCCESS
                this.uriImageSelected = data.getData();
                Glide.with(this) //SHOWING PREVIEW OF IMAGE
                        .load(this.uriImageSelected)
                        .into(this.imageViewPreview);
            } else {
                Toast.makeText(getContext(), "No photo selected", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // -------------------------
    // COMMUNICATE WITH ACTIVITY
    // -------------------------

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);

        try {
            mCallback = (EditionFragment.OnEditionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnPropertiesListSelectedListener");
        }
    }
}
