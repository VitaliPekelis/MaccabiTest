package com.vitalipek.maccabitestapp.ui;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.vitalipek.maccabitestapp.R;

/**
 * Created by Vitali on 28/07/2017.
 */

public abstract class BaseActivity extends AppCompatActivity
{
    private View progressBar;

    @Override
    public void setContentView(@LayoutRes int layoutResID)
    {
        View baseLayout = getLayoutInflater().inflate(R.layout.activity_base, null, false);

        ViewStub actContent = baseLayout.findViewById(R.id.base_vs_activity_content);
        actContent.setLayoutResource(layoutResID);
        actContent.inflate();

        super.setContentView(baseLayout);
        initUi();
    }

    private void initUi()
    {
        progressBar = findViewById(R.id.base_pb_container);
    }

    public void startProgress()
    {
        if(progressBar!=null)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void stopProgress()
    {
        if(progressBar!=null)
        {
            progressBar.setVisibility(View.GONE);
        }
    }

    protected void setTitle(String title)
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
    }

}
