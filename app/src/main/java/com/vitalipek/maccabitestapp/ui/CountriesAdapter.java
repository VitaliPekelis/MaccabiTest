package com.vitalipek.maccabitestapp.ui;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vitalipek.maccabitestapp.R;
import com.vitalipek.maccabitestapp.models.Country;
import com.vitalipek.maccabitestapp.ui.interfaces.IRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitali on 29/07/2017.
 */

public class CountriesAdapter extends RecyclerView.Adapter
{
    private List<Country>         mData;
    private IRecyclerViewListener mListener;

    public CountriesAdapter(List<Country> data, IRecyclerViewListener listener)
    {
        this.mData = data == null ? new ArrayList<Country>() : data;
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new CountryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        final Country itemData = mData.get(position);
        CountryHolder countryHolder = (CountryHolder)holder;

        if (itemData!=null)
        {
            if(!TextUtils.isEmpty(itemData.nativeName)) countryHolder.nativeName.setText(itemData.nativeName);
            if(!TextUtils.isEmpty(itemData.name)) countryHolder.name.setText(itemData.name);

            countryHolder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if(mListener!=null)
                    {
                        mListener.onItemClick(itemData);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mData.size();
    }

    public void setData(List<Country> data)
    {
        this.mData = data == null ? new ArrayList<Country>() : data;
    }


    public static class CountryHolder extends RecyclerView.ViewHolder
    {
        TextView     name, nativeName;


        public CountryHolder(View view)
        {
            super(view);
            name = view.findViewById(R.id.item_country_tv_name);
            nativeName = view.findViewById(R.id.item_country_tv_native_name);
        }
    }
 }
