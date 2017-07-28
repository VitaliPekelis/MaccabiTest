package com.vitalipek.maccabitestapp.ui.firstScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.ui.BaseActivity;
import com.vitalipek.maccabitestapp.ui.interfaces.IRecyclerViewListener;
import com.vitalipek.maccabitestapp.ui.secondScreen.SecondActivity;

public class FirstActivity extends BaseActivity implements IRecyclerViewListener
{
    private CountriesAdapter mAdapter;

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
        setTitle(FirstActivity.class.getSimpleName());
        initRecyclerView();
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

    }

    //----------------------------------------------------------------------------------------------
    // IRecyclerViewListener - implementation
    //----------------------------------------------------------------------------------------------
    @Override
    public void onItemClick()
    {
        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        //intent.putExtra();
        startActivity(intent);
    }
}
