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

    //STATIC DATA FOR PICTURE
    private static final String PERMS = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    private Uri uriImageSelected;

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

        this.configureEditionButton();

        return view;

    }

    private void configureEditionButton(){
        editionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEditionMode){
                    property.setArea(editTextArea.getText().toString());
                    propertyEdited = property;
                }else {
                    /*
                    String area = editTextArea.getText().toString();
                    long id = 25;
                    double latitude = 48.300000;
                    double longitude = -4.470000;

                    propertyEdited = new Property(id, area, latitude, longitude);
                    */
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

        this.configureRadioGroup();
        if(p.isSold() == 1){
            radioBtnSold.setChecked(true);
        }else{
            radioBtnNotSold.setChecked(true);
        }

        this.configureCheckboxes();

        if(p.getPoiSchool() != 0) checkboxSchool.setChecked(true);
        if(p.getPoiPark() != 0) checkboxPark.setChecked(true);
        if(p.getPoiShopping() != 0) checkboxShopping.setChecked(true);
        if(p.getPoiMetro() != 0) checkboxMetro.setChecked(true);
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


    //-----------------------------------
    // CHECK BOXES
    //-----------------------------------

    private void configureCheckboxes(){

        CheckBox [] checkBoxes = {checkboxSchool, checkboxShopping, checkboxPark, checkboxMetro};

        for (CheckBox checkBox : checkBoxes){
            checkBox.setOnCheckedChangeListener(this::onCheckedChanged);
        }

    }

    public int [] checkboxesSelected(boolean [] array){

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
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId) {
            case R.id.search_sold:
                //statusSold = true;
                break;
            case R.id.search_not_sold:
                //statusSold = false;
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
}
