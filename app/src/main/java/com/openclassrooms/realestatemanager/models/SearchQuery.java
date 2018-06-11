package com.openclassrooms.realestatemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchQuery implements Parcelable {

    private int age;
    private String name;


    //CONSTRUCTOR
    public SearchQuery(int age, String name){
        this.age = age;
        this.name = name;
    }

    //GETTERS
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    //PARCELABLE METHODS

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(age);
        out.writeString(name);
    }

    public static final Parcelable.Creator<SearchQuery> CREATOR
            = new Parcelable.Creator<SearchQuery>() {

        public SearchQuery createFromParcel(Parcel in) {
            return new SearchQuery(in);
        }

        public SearchQuery[] newArray(int size) {
            return new SearchQuery[size];
        }
    };

    private SearchQuery(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }
}
