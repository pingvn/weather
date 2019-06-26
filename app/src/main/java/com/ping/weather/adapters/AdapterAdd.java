package com.ping.weather.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.ping.weather.R;
import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.CitySqlData;

import java.util.ArrayList;
import java.util.List;

public class AdapterAdd extends RecyclerView.Adapter<AdapterAdd.ViewHolder> {

    private List<CitySqlData> mListCity = new ArrayList<>();
    Context mContex;

    public AdapterAdd(List<CitySqlData> mList, Context mContex) {
        this.mListCity = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_find_city, viewGroup, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CitySqlData mCity = mListCity.get(i);
        viewHolder.mNmaeCity.setText(mCity.getmName());
        viewHolder.mCountryCity.setText(mCity.getmCountry());
    }


    @Override
    public int getItemCount() {
        return mListCity.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNmaeCity;
        private TextView mCountryCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNmaeCity = itemView.findViewById(R.id.id_text_city_name);
            mCountryCity = itemView.findViewById(R.id.id_text_city_country);
        }
    }
}
