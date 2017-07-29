package com.vitalipek.maccabitestapp.network.controllers;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.vitalipek.maccabitestapp.global.AppConstants;
import com.vitalipek.maccabitestapp.models.Country;
import com.vitalipek.maccabitestapp.network.requests.MyJsonRequest;

/**
 * Created by Vitali on 28/07/2017.
 */

public class MainController extends BaseController
{

    public void getAll(String url, Response.Listener<Country[]> iListener, Response.ErrorListener iErrorListener)
    {
        if (!isRequestQueueNull())
        {
            MyJsonRequest<Country[]> request = new MyJsonRequest<>(Request.Method.GET, url, null, Country[].class, iListener, iErrorListener);
            request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setTag(AppConstants.TAG_GET_All);
            getRequestQueue().cancelAll(AppConstants.TAG_GET_All);
            getRequestQueue().add(request);
        }
    }

    public void getBorders(String url, Response.Listener<Country[]> iListener, Response.ErrorListener iErrorListener)
    {
        if (!isRequestQueueNull())
        {
            MyJsonRequest<Country[]> request = new MyJsonRequest<>(Request.Method.GET, url, null, Country[].class, iListener, iErrorListener);
            request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            request.setTag(AppConstants.TAG_GET_BORDERS);
            getRequestQueue().cancelAll(AppConstants.TAG_GET_BORDERS);
            getRequestQueue().add(request);
        }
    }
}