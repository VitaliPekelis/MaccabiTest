package com.vitalipek.maccabitestapp.network.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.vitalipek.maccabitestapp.BuildConfig;

import java.io.IOException;

/**
 * Created by vitaliP on 12/01/2016.
 */
public class Mapper {
    private static final String TAG = Mapper.class.getSimpleName();
    private static Gson MAPPER;

    public static Gson get()
    {
        if (MAPPER == null)
        {
            MAPPER = new Gson();
        }

        return MAPPER;
    }
    public static String string(Object data)
    {
        try
        {
            String result = get().toJson(data);
            if(BuildConfig.DEBUG) Log.i(TAG, "toJson: "+result);
            return result;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T objectOrThrow(String data,
                                      Class<T> type) throws JsonParseException, IOException
    {
        if(BuildConfig.DEBUG) Log.i(TAG, "fromJson: "+data);
        T result = get().fromJson(data, type);
        return result;
    }

    public static <T> T object(String data, Class<T> type)
    {
        try {
            return objectOrThrow(data, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
