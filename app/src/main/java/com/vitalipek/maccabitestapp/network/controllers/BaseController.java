package com.vitalipek.maccabitestapp.network.controllers;

import com.android.volley.RequestQueue;

/**
 * Created by Vitali on 28/07/2017.
 */

public abstract class BaseController
{
    protected static final int CONNECTION_TIME_OUT = 20000;
    private RequestQueue mRequestQueue;

    public void setVolleyRequestQueue(RequestQueue iRequestQueue) {
        this.mRequestQueue = iRequestQueue;
    }

    protected RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public boolean isRequestQueueNull() {
        return mRequestQueue == null;
    }


    /**Vitali sample
     *
     * @param iListener
     * @param iErrorListener
     */
    /*public void sampleRequest(Response.Listener<String> iListener, Response.ErrorListener iErrorListener)
    {
        if (!isRequestQueueNull())
        {
            StringRequest request = new StringRequest(Request.Method.GET, "http://www.google.com", iListener, iErrorListener);

            request.setRetryPolicy(new DefaultRetryPolicy(CONNECTION_TIME_OUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //request.setTag(AppConstants.TAG_SAMPLE);
            //getRequestQueue().cancelAll(AppConstants.TAG_SAMPLE);
            getRequestQueue().add(request);
        }
    }*/
}
