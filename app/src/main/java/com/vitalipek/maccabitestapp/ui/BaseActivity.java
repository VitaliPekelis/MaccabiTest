package com.vitalipek.maccabitestapp.ui;

import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import com.android.volley.NetworkError;
import com.android.volley.VolleyError;
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

    protected void onErrorResponseArrive(VolleyError error, @Nullable final Runnable r)
    {
        if (error != null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            if(r!=null)
            {
                builder.setPositiveButton(
                        R.string.retry,
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                r.run();
                            }
                        }
                );
            }

            if (error instanceof NetworkError)
            {
                builder.setTitle(R.string.oops);
                builder.setMessage(getString(R.string.no_network_connection));
            }
            else
            {
                builder.setTitle(R.string.something_went_wrong);
                builder.setMessage(getString(R.string.we_working_on_it));
            }

            builder.create().show();
            error.printStackTrace();
        }
    }
}
