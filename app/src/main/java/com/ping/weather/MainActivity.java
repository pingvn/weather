package com.ping.weather;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ping.weather.fragments.AddCityFragment;
import com.ping.weather.fragments.WheatherFragment;

public class MainActivity extends AppCompatActivity implements WheatherFragment.OnFragmentInteractionListener, AddCityFragment.OnFragmentInteractionListener {
    public static final String CITY_LIST = "mycity";
    public static final String CITY_LIST_KEY = "city";
    public static final String CITY_LON = "lon";
    public static final String CITY_LAT = "lat";


    public SharedPreferences mCitySetting;
    private String mSaveCity;
    private double mlat;
    private double mlon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSettings();
        getLocation();

        getSupportFragmentManager().beginTransaction().add(R.id.id_FragmentConteiner, new WheatherFragment()).commit();
    }

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            getSupportFragmentManager().beginTransaction().replace(R.id.id_FragmentConteiner,WheatherFragment.newInstance("Moscow London Paris",location.getLatitude(),location.getLongitude())).commit();
            SharedPreferences.Editor mEdit = mCitySetting.edit();
            mEdit.putFloat(CITY_LAT, (float) location.getLatitude());
            mEdit.putFloat(CITY_LON,(float) location.getLongitude());
            mEdit.apply();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void getLocation() {
        LocationManager mLocation = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 150000, 1000, mLocationListener);
    }

    private void getSettings(){
        mCitySetting = getSharedPreferences(CITY_LIST, Context.MODE_PRIVATE);

        if (mCitySetting.contains(CITY_LIST_KEY)) {
            mSaveCity = mCitySetting.getString(CITY_LIST_KEY, null);
            mlat = mCitySetting.getFloat(CITY_LAT, 12.33f);
            mlon = mCitySetting.getFloat(CITY_LON,32.56f);
            getSupportFragmentManager().beginTransaction().replace(R.id.id_FragmentConteiner,WheatherFragment.newInstance("Moscow London Paris",mlat,mlon)).commit();
        }
    }

    @Override
    public void onFragmentWeatherInteraction(int mId) {
        switch (mId){
            case 0 :{
                getSupportFragmentManager().beginTransaction().replace(R.id.id_FragmentConteiner,new AddCityFragment()).commit();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
