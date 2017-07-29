package com.vitalipek.maccabitestapp.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.global.AppConstants;
import com.vitalipek.maccabitestapp.models.Country;
import com.vitalipek.maccabitestapp.network.NetworkingHandler;
import com.vitalipek.maccabitestapp.network.utils.RequestStringBuilder;
import com.vitalipek.maccabitestapp.network.controllers.MainController;

import java.util.Arrays;
import java.util.List;

public class SecondActivity extends BaseActivity
{
    private Country mCountry;
    private CountriesAdapter mAdapter;
    private Runnable mRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getExtras();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initRetry(savedInstanceState);
        initUi();
        setupData();
    }

    private void initRetry(Bundle savedInstanceState)
    {
        if(savedInstanceState == null)
        {
            mRetry = new Runnable()
            {
                @Override
                public void run()
                {
                    fetchData();
                }
            };
        }
    }

    private void setupData()
    {
        if(!TextUtils.isEmpty(mCountry.getBorders()))
        {
            fetchData();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(mCountry.name)
                    .setMessage(R.string.this_country_dont_have_borders)
                    .setPositiveButton(R.string.done, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {
                            finish();
                        }
                    }).create().show();
        }
    }

    private void fetchData()
    {
        try
        {
            startProgress();
            NetworkingHandler.getInstance().getController(MainController.class).getBorders(
                    RequestStringBuilder.getBordersUrl(mCountry.getBorders()),
                    new Response.Listener<Country[]>()
                    {
                        @Override
                        public void onResponse(Country[] response)
                        {
                            stopProgress();
                            if(response!=null&& response.length > 0)
                                onResponseArrived(Arrays.asList(response));
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            stopProgress();
                            onErrorResponseArrive(error, mRetry);
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

    private void onResponseArrived(List<Country> data)
    {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    private void getExtras()
    {
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            mCountry = (Country) extras.get(AppConstants.EXTRA_COUNTRY);
        }
        else
        {
            finish();
        }
    }

    private void initUi()
    {
        setTitle(SecondActivity.class.getSimpleName());
        setupHeader();
        initRecyclerView();
    }

    private void setupHeader()
    {
        ((TextView)findViewById(R.id.second_activity_tv_header_title)).setText(Html.fromHtml(getString(R.string.borders_of, mCountry.name)));
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerV = (RecyclerView) findViewById(R.id.second_activity_rv);
        recyclerV.setHasFixedSize(true);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CountriesAdapter(null, /*IRecyclerViewListener*/null);
        recyclerV.setAdapter(mAdapter);
    }

}
