package com.vitalipek.maccabitestapp.network;

import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.vitalipek.maccabitestapp.network.controllers.BaseController;

/**
 * Created by vitalip on 03/07/2016.
 */
public class NetworkingHandler
{
    private static NetworkingHandler instance;
    private static boolean isInitialized = false;
    private ArrayMap<String, BaseController> mControllers;
    private RequestQueue                     mRequestQueue;

    public static NetworkingHandler getInstance()
    {
        synchronized(NetworkingHandler.class)
        {
            checkIfInitialized();
            return instance;
        }
    }

    private NetworkingHandler()
    {
        mControllers = new ArrayMap<>();
    }

    public static void initNetworkingHandler(Context appContext)
    {
        if(instance == null)
        {
            instance = new NetworkingHandler();
            instance.getRequestQueue(appContext);
            isInitialized = true;
        }
    }

    private RequestQueue getRequestQueue(Context appContext)
    {
        if(mRequestQueue==null)
            mRequestQueue = Volley.newRequestQueue(appContext);
        return  mRequestQueue;
    }

    /*Volley*/

    /**
     * Retrieves the wanted controller by the controller type from the controllers ArrayMap.<BR>
     * If this is the first time retrieving the controller it will be created and added to the controllers ArrayMap.<BR>
     *
     * @param iControllerClass - The class of the controller to retrive. Must implement IController.
     * @return The instance of the wanted controller.
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T extends BaseController> T getController(Class<T> iControllerClass) throws IllegalAccessException, InstantiationException
    {
        T result = (T) mControllers.get(iControllerClass.getSimpleName());

        if (result == null)
        {
            result = iControllerClass.newInstance();
            result.setVolleyRequestQueue(mRequestQueue);
            mControllers.put(iControllerClass.getSimpleName(), result);
        }
        return result;
    }

    private static boolean checkIfInitialized()
    {
        if(!isInitialized)
        {
            throw new ExceptionInInitializerError("must Call initNetworkingHandler()");
        }

        return true;
    }
}
