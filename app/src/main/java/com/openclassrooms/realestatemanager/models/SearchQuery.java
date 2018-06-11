package com.openclassrooms.realestatemanager.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchQuery implements Parcelable {

    private String area;


    //CONSTRUCTOR
    public SearchQuery(String area){
        this.area = area;
    }

    //GETTERS
    public String getArea() {
        return area;
    }

    //PARCELABLE METHODS

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(area);
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
        area = in.readString();
    }
}
