package com.vitalipek.maccabitestapp.ui.secondScreen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.ui.BaseActivity;
import com.vitalipek.maccabitestapp.ui.firstScreen.CountriesAdapter;
import com.vitalipek.maccabitestapp.ui.interfaces.IRecyclerViewListener;

public class SecondActivity extends BaseActivity implements IRecyclerViewListener
{
    private CountriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initUi();
    }

    private void initUi()
    {
        setTitle(SecondActivity.class.getSimpleName());
        initRecyclerView();
        /*new Handler(getMainLooper()).postDelayed(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        startProgress();
                    }
                }
                ,
                1000);

        new Handler(getMainLooper()).postDelayed(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        stopProgress();
                    }
                }
                ,
                5000);*/
    }

    private void initRecyclerView()
    {
        RecyclerView recyclerV = (RecyclerView) findViewById(R.id.second_activity_rv);
        recyclerV.setHasFixedSize(true);
        recyclerV.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CountriesAdapter(null, /*IRecyclerViewListener*/this);
        recyclerV.setAdapter(mAdapter);
    }

    //----------------------------------------------------------------------------------------------
    // IRecyclerViewListener - implementation
    //----------------------------------------------------------------------------------------------
    @Override
    public void onItemClick()
    {

    }
}
