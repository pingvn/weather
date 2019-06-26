package com.ping.weather.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ping.weather.R;
import com.ping.weather.datamodel.City;
import com.ping.weather.datamodel.Weather;
import com.ping.weather.network.Network;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHoldeer> {

    private List<City> mListCity = new ArrayList<>();

    public Adapter(List<City> mList) {
        this.mListCity = mList;
    }


    @NonNull
    @Override
    public ViewHoldeer onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_card, viewGroup, false);
        return new ViewHoldeer(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHoldeer viewHoldeer, int i) {
        City mCity = mListCity.get(i);
        if (mCity.getName().equals(" ")) {
            Network.getInstance().getApi().getWeathercoord(mCity.getCoord().getLat(),mCity.getCoord().getLon(), "metric", viewHoldeer.lang, viewHoldeer.key).enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    Weather mWeather = response.body();
                    viewHoldeer.mName.setText(mWeather.getName());
                    viewHoldeer.mTemp.setText(mWeather.getMain().getTemp().toString());
                    viewHoldeer.mDescription.setText(mWeather.getWeather().get(0).getDescription());
                    Picasso.get().load("http://openweathermap.org/img/w/" + mWeather.getWeather().get(0).getIcon() + ".png").into(viewHoldeer.mIcon);
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {

                }
            });


        } else {
            Network.getInstance().getApi().getWeather(mCity.getName(), "metric", viewHoldeer.lang, viewHoldeer.key).enqueue(new Callback<Weather>() {
                @Override
                public void onResponse(Call<Weather> call, Response<Weather> response) {
                    Weather mWeather = response.body();
                    viewHoldeer.mName.setText(mWeather.getName());
                    viewHoldeer.mTemp.setText(mWeather.getMain().getTemp().toString());
                    viewHoldeer.mDescription.setText(mWeather.getWeather().get(0).getDescription());
                    Picasso.get().load("http://openweathermap.org/img/w/" + mWeather.getWeather().get(0).getIcon() + ".png").into(viewHoldeer.mIcon);
                }

                @Override
                public void onFailure(Call<Weather> call, Throwable t) {

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mListCity.size();
    }

    class ViewHoldeer extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mTemp;
        private TextView mDescription;
        private ImageView mIcon;
        private String lang;
        private String key;

        public ViewHoldeer(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.id_nameCity);
            mTemp = itemView.findViewById(R.id.id_temp);
            mDescription = itemView.findViewById(R.id.id_description);
            mIcon = itemView.findViewById(R.id.id_imageView_icon);
            lang = itemView.getResources().getString(R.string.lang);
            key = itemView.getResources().getString(R.string.key);
        }
    }
}
