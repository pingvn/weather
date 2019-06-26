package com.ping.weather.readfile;

import android.content.Context;

import com.ping.weather.R;
import com.ping.weather.datamodel.City;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReafFile {
    private static final String SIPARETE_CITY_NAME_COUNTRY = "::";
    private Context mContexct;
    public ReafFile(Context context) {
        this.mContexct = context;
    }

    public List<City> readCityFile()throws IOException {
        List<City> mListCity = new ArrayList<>();
        String mStrBuffer;
        StringBuilder mStringBilder = new StringBuilder();
       BufferedReader mBufferReader = new BufferedReader(new InputStreamReader(mContexct.getResources().openRawResource(R.raw.city)));
        while ((mStrBuffer = mBufferReader.readLine())!= null){
            String[] mBufferCity = mStrBuffer.split(SIPARETE_CITY_NAME_COUNTRY);
            City mCity = new City();
            if(mBufferCity.length > 1){
                mCity.setName(mBufferCity[0]);
                mCity.setCountry(mBufferCity[1]);
            }else{
                mCity.setName(mBufferCity[0]);
                mCity.setCountry("-");
            }
            mListCity.add(mCity);
        }
        return mListCity;
    }

}
