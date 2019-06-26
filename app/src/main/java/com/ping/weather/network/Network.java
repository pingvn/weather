package com.ping.weather.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

    private static Network mInstance;
    private Retrofit mRetrofit;

    private Network(){
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Network getInstance(){
        if(mInstance == null)
        {
            mInstance = new Network();
        }
        return mInstance;
    }

    public OpenWeatherApi getApi(){
        return mRetrofit.create(OpenWeatherApi.class);
    }


}
