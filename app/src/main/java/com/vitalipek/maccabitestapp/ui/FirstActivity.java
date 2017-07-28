package com.vitalipek.maccabitestapp.ui;

import android.os.Bundle;
import android.os.Handler;

import com.vitalipek.maccabitestapp.R;

public class FirstActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initUi();
        fetchData();
    }

    private void initUi()
    {

        new Handler(getMainLooper()).postDelayed(
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
                5000);
    }

    private void fetchData()
    {

    }
}
