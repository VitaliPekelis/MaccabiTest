package com.vitalipek.maccabitestapp.network.utils;

import android.util.Log;

import com.vitalipek.maccabitestapp.BuildConfig;

/**
 * Created by Vitali on 28/07/2017.
 */

public class RequestStringBuilder
{
    private static final String TAG = RequestStringBuilder.class.getSimpleName();

    public static String HOST_PRODUCTION = "https://restcountries.eu";
    //public static String HOST_BETA = "some beta host";
    public static String HOST            = HOST_PRODUCTION;

    //GET
    private static final String GET_ALL = "/rest/v2/all";
    private static final String GET_BORDERS = "/rest/v2/alpha?codes=";

    //POST

    public final static String getAllUrl()
    {
        String url = HOST + GET_ALL;
        if(BuildConfig.DEBUG) Log.d(TAG, "getAllUrl(): "+url);
        return url;
    }

    public static String getBordersUrl(String countryCods)
    {
        String url = HOST + GET_BORDERS + countryCods;
        if(BuildConfig.DEBUG) Log.d(TAG, "getBordersUrl(): "+url);
        return url;
    }
}
