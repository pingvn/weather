package com.ping.weather.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ping.weather.R;
import com.ping.weather.adapters.AdapterAdd;
import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.CitySqlData;
import com.ping.weather.parsjson.JsonParse;
import com.ping.weather.readfile.ReafFile;
import com.ping.weather.sql.SqlHelper;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCityFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;
    private EditText mEditText;
    private OnFragmentInteractionListener mListener;
    private List<City> mList = new ArrayList<>();
    private AdapterAdd mAdapter;
    private RecyclerView mRecyclerView;
    private SqlHelper mDataBase;

    public AddCityFragment() {
    }

    public static AddCityFragment newInstance(String param1, String param2) {
        AddCityFragment fragment = new AddCityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View mView = inflater.inflate(R.layout.fragment_add_city, container, false);
        init(mView);
        return mView;
    }
    void init(View mView){
        mEditText = mView.findViewById(R.id.editText);
        mRecyclerView = mView.findViewById(R.id.id_find_city_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
       // mAdapter = new AdapterAdd(mList,getContext());
       // mRecyclerView.setAdapter(mAdapter);
        mDataBase = new SqlHelper(mView.getContext());

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 2){
                    List<CitySqlData> mListCity = new ArrayList<>();
                    mListCity = mDataBase.getCity(s.toString());
                    mAdapter = new AdapterAdd(mListCity,getContext());
                    mRecyclerView.setAdapter(mAdapter);
                }

            }
        });
        
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
        void onFragmentInteraction(Uri uri);
    }
}
