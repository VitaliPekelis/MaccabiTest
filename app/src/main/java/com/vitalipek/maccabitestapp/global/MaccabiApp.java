package com.vitalipek.maccabitestapp.global;

import android.app.Application;

import com.vitalipek.maccabitestapp.network.NetworkingHandler;

/**
 * Created by Vitali on 28/07/2017.
 */

public class MaccabiApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        /*instanse = this;*/
        NetworkingHandler.initNetworkingHandler(MaccabiApp.this);
    }
}
