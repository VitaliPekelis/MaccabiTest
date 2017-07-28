package com.vitalipek.maccabitestapp.ui.firstScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali on 29/07/2017.
 */

public class CountriesAdapter extends RecyclerView.Adapter
{
    private List<Object> mData;
    private IRecyclerViewListener mListener;

    public CountriesAdapter(List<Object> data, IRecyclerViewListener listener)
    {
        this.mData = data == null ? new ArrayList<>() : data;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new CountryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mListener!=null)
                {
                    mListener.onItemClick();
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return /*mData.size()*/20;
    }

    public static class CountryHolder extends RecyclerView.ViewHolder
    {

        public CountryHolder(View itemView)
        {
            super(itemView);
        }
    }
 }
