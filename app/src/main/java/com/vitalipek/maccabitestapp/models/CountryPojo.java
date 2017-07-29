package com.vitalipek.maccabitestapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vitali on 29/07/2017.
 */

public class CountryPojo
{
    @SerializedName("name")
    public String       name;
    @SerializedName("nativeName")
    public String       nativeName;
    @SerializedName("borders")
    public List<String> borders;
}
