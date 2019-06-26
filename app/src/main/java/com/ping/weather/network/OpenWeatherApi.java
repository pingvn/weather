package com.ping.weather.network;

import com.ping.weather.datamodel.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {
    @GET("weather")
    public Call<Weather> getWeather(@Query("q") String city,
                                    @Query("units") String units,
                                    @Query("lang") String lang,
                                    @Query("appid") String appid);
    @GET("weather")
    public Call<Weather> getWeathercoord(@Query("lat") double lat,
                                    @Query("lon") double lon,
                                    @Query("units") String units,
                                    @Query("lang") String lang,
                                    @Query("appid") String appid);
}
