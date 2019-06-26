package com.ping.weather.sql;

import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.CitySqlData;

import java.util.List;

public interface SqlInterface {
    public void addCity(City mCity);
    public List<CitySqlData> getCity(String mNameCity);
    public void deleteAll();


}
