package com.vitalipek.maccabitestapp.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.vitalipek.maccabitestapp.BuildConfig;


/**
 * Created by Vitali on 29/07/2017.
 */

public class Country extends CountryPojo implements Parcelable
{
    private final static String TAG = Country.class.getSimpleName();
    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.name);
        dest.writeString(this.nativeName);
        dest.writeStringList(this.borders);
    }

    public Country() {}

    protected Country(Parcel in)
    {
        this.name = in.readString();
        this.nativeName = in.readString();
        this.borders = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CountryPojo> CREATOR = new Parcelable.Creator<CountryPojo>()
    {
        @Override
        public CountryPojo createFromParcel(Parcel source) {return new Country(source);}

        @Override
        public CountryPojo[] newArray(int size) {return new CountryPojo[size];}
    };

    public String getBorders()
    {
        StringBuilder builder = new StringBuilder();
        for (String countryCode : borders)
        {
            builder.append(countryCode)
                    .append("\u003B");
        }
        if(BuildConfig.DEBUG) Log.d(TAG, "getBorders() called with borders: = ["+builder.toString()+"]");
        return builder.toString();
    }
}
