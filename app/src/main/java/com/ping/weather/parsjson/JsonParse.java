package com.ping.weather.parsjson;


import android.content.Context;

import com.ping.weather.R;
import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.Coord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonParse {
    private JSONObject mCityJson;
    private City mCityModel;
    private final static String NEW_LINE = "\n";

    private final static String JSON_ID = "id";
    private final static String JSON_NAME = "name";
    private final static String JSON_COUNTRY = "country";
    private final static String JSON_ARRAY_COORD = "coord";
    private final static String JSON_LON = "lon";
    private final static String JSON_LAT = "lat";

    /*
    public List<City> parseJson(Context mContext) throws IOException, JSONException {
        List<City> mListCity = new ArrayList<>();
        String mJsonBuffer = readJson(mContext, R.raw.citylist);
        JSONArray mJsonArray = new JSONArray(mJsonBuffer);
        for (int i = 0; i < mJsonArray.length(); i++) {
            JSONObject mJBuffer = mJsonArray.getJSONObject(i);
            City mCityBuffer = new City();
            mCityBuffer.setId(mJBuffer.getInt(JSON_ID));
            mCityBuffer.setName(mJBuffer.getString(JSON_NAME));
            mCityBuffer.setCountry(mJBuffer.getString(JSON_COUNTRY));
            JSONObject mJsonCoord = mJBuffer.getJSONObject(JSON_ARRAY_COORD);
            Coord mCoordBuffer = new Coord();
            mCoordBuffer.setLon(mJsonCoord.getDouble(JSON_LON));
            mCoordBuffer.setLat(mJsonCoord.getDouble(JSON_LAT));
            mCityBuffer.setCoord(mCoordBuffer);
            mListCity.add(mCityBuffer);
        }
        return mListCity;
    }


    private String readJson(Context mContext, int resurceId) throws IOException {
        InputStream mInputStream = mContext.getResources().openRawResource(resurceId);
        BufferedReader mBufereReader = new BufferedReader(new InputStreamReader(mInputStream));
        StringBuilder mBufferString = new StringBuilder();
        String mBuffer = null;
        while ((mBuffer = mBufereReader.readLine()) != null) {
            mBufferString.append(mBuffer);
            mBufferString.append(NEW_LINE);
        }
        return mBufferString.toString();
    }
    */
}
