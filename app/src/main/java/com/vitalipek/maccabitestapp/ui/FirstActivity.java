package com.vitalipek.maccabitestapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.global.AppConstants;
import com.vitalipek.maccabitestapp.models.Country;
import com.vitalipek.maccabitestapp.network.NetworkingHandler;
import com.vitalipek.maccabitestapp.network.utils.RequestStringBuilder;
import com.vitalipek.maccabitestapp.network.controllers.MainController;
import com.vitalipek.maccabitestapp.ui.interfaces.IRecyclerViewListener;

import java.util.Arrays;
import java.util.List;

public class FirstActivity extends BaseActivity implements IRecyclerViewListener
{
    private CountriesAdapter mAdapter;
    private Runnable         mRetryFetchData;
    private List<Country>    mData;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
        {
            mRetryFetchData = new Runnable()
            {
                @Override
                public void run()
                {
                    fetchData();
                }
            };
        }

        setContentView(R.layout.activity_first);
        initUi();
        fetchData();
    }

    private void initUi()
    {
        setTitle(FirstActivity.class.getSimpleName());
        initRecyclerView();

        /*SVGImageView imageView = (SVGImageView) findViewById(R.id.item_country_iv);
        imageView.setSVG();*/
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerV = (RecyclerView) findViewById(R.id.first_activity_rv);
        recyclerV.setHasFixedSize(true);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CountriesAdapter(null, /*IRecyclerViewListener*/this);
        recyclerV.setAdapter(mAdapter);
    }

    private void fetchData()
    {
        try
        {
            startProgress();
            NetworkingHandler.getInstance().getController(MainController.class).getAll(
                    RequestStringBuilder.getAllUrl(),
                    new Response.Listener<Country[]>()
                    {
                        @Override
                        public void onResponse(Country[] response)
                        {
                            stopProgress();
                            if (response != null && response.length > 0)
                            {
                                onResponseArrived(Arrays.asList(response));
                            }

                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            stopProgress();
                            onErrorResponseArrive(error, mRetryFetchData);
                        }
                    }
            );
        }
        catch (Exception e)
        {
            stopProgress();
            e.printStackTrace();
        }
    }

    private void onResponseArrived(List<Country> countries)
    {
        mData = countries;
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
    }

    //----------------------------------------------------------------------------------------------
    // IRecyclerViewListener - implementation
    //----------------------------------------------------------------------------------------------
    @Override
    public void onItemClick(Country country)
    {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra(AppConstants.EXTRA_COUNTRY, country);
        startActivity(intent);
    }
}
