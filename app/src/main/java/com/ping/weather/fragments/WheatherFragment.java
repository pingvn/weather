package com.ping.weather.fragments;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ping.weather.R;
import com.ping.weather.adapters.Adapter;
import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.Coord;

import java.util.ArrayList;
import java.util.List;


public class WheatherFragment extends Fragment {
    private static final String ARG_PARAM1 = "city";
    private static final String ARG_LAT = "lat";
    private static final String ARG_LOT = "lot";
    private List<City> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private FloatingActionButton mAddCity;

    // TODO: Rename and change types of parameters
    private String mCityString;
    private double lot;
    private double lat;

    private OnFragmentInteractionListener mListener;

    public WheatherFragment() {
        // Required empty public constructor
    }


    public static WheatherFragment newInstance(String mCityList, double lat, double lot) {
        WheatherFragment fragment = new WheatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mCityList);
        args.putDouble(ARG_LAT, lat);
        args.putDouble(ARG_LOT, lot);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCityString = getArguments().getString(ARG_PARAM1);
            lot = getArguments().getDouble(ARG_LOT);
            lat = getArguments().getDouble(ARG_LAT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_wheather, container, false);
        initButton(mView);
        initRecyclerView(mView);
        return mView;
    }

    private void initButton(View mView) {
        mAddCity = mView.findViewById(R.id.floatingActionButton);
        mAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentWeatherInteraction(0);
            }
        });

    }

    private void initRecyclerView(View mView) {
        mRecyclerView = mView.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        if (mCityString != null) {
            mList.clear();
            City mCity = new City();
            Coord mCoord = new Coord();
            mCoord.setLat(lat);
            mCoord.setLon(lot);
            mCity.setCoord(mCoord);
            mCity.setName(" ");
            mList.add(mCity);
            String[] buff;
            buff = mCityString.split(" ");
            for (String st : buff) {
                mCity = new City();
                mCity.setName(st);
                mList.add(mCity);
            }

        } else {
            mList.clear();
            City mCity = new City();
            mCity.setName("Moscow");
            mList.add(mCity);
        }

        mAdapter = new Adapter(mList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentWeatherInteraction(int mId);
    }
}
