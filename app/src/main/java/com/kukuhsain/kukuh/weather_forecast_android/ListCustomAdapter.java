package com.kukuhsain.kukuh.weather_forecast_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by kukuh on 17/03/16.
 */
public class ListCustomAdapter extends ArrayAdapter<ForecastDataList> {

    public ListCustomAdapter(Context context, ForecastDataList[] forecastDataLists) {
        super(context, R.layout.custom_row, forecastDataLists);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        ForecastDataList list = getItem(position);

        final ImageView weatherIcon = (ImageView) customView.findViewById(R.id.weather_icon);
        TextView weatherMain = (TextView) customView.findViewById(R.id.weather_main);
        TextView weatherDescription = (TextView) customView.findViewById(R.id.weather_description);
        TextView temperature = (TextView) customView.findViewById(R.id.temperature);

        weatherMain.setText(list.weather.get(0).main);
        weatherDescription.setText(list.weather.get(0).description);
        temperature.setText("Temperature " + list.temp.min + "C - " + list.temp.max + "C");

        String image_url = "http://api.openweathermap.org/img/w/" + list.weather.get(0).icon + ".png";
        Log.d("url", image_url);

        Glide.with(getContext())
                .load(image_url)
                .centerCrop()
//                .placeholder(R.drawable.)
                .crossFade()
                .into(weatherIcon);

        return customView;
    }

}
