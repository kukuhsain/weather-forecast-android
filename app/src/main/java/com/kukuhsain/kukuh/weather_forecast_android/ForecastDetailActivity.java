package com.kukuhsain.kukuh.weather_forecast_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

//import com.kukuhsain.kukuh.weather_forecast_android.DataClass.*;

public class ForecastDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Gson gson = new Gson();
        String stringCity = getIntent().getStringExtra("city");
        String stringSingleList = getIntent().getStringExtra("single-list");

        City city = gson.fromJson(stringCity, City.class);
        ForecastDataList list = gson.fromJson(stringSingleList, ForecastDataList.class);

        Log.d("city", stringCity);
        Log.d("list", stringSingleList);

        TextView cityCountry = (TextView) findViewById(R.id.detail_city_country);
        ImageView weatherIcon = (ImageView) findViewById(R.id.detail_icon);
        TextView weatherMain = (TextView) findViewById(R.id.detail_weather_main);
        TextView weatherDesc = (TextView) findViewById(R.id.detail_weather_desc);
        TextView wind = (TextView) findViewById(R.id.detail_wind);
        TextView cloudiness = (TextView) findViewById(R.id.detail_cloudiness);
        TextView pressure = (TextView) findViewById(R.id.detail_pressure);
        TextView humidity = (TextView) findViewById(R.id.detail_humidity);
        TextView rangeTemp = (TextView) findViewById(R.id.detail_range_temp);
        TextView timeTemp = (TextView) findViewById(R.id.detail_time_temp);
        TextView coord = (TextView) findViewById(R.id.detail_coord);

        cityCountry.setText(city.name + ", " + city.country);
        weatherMain.setText(list.weather.get(0).main);
        weatherDesc.setText(list.weather.get(0).description);
        wind.setText(list.speed + " m/sec");
        cloudiness.setText(list.clouds + " %");
        pressure.setText(list.pressure + " hPa");
        humidity.setText(list.humidity + " %");
        rangeTemp.setText(list.temp.min + "C - " + list.temp.max + "C");
        timeTemp.setText(list.temp.morn + "C, " + list.temp.day + "C, " + list.temp.eve + "C, " + list.temp.night + "C");
        coord.setText("[ " + city.coord.lat + ", " + city.coord.lon + " ]");

        String image_url = "http://api.openweathermap.org/img/w/" + list.weather.get(0).icon + ".png";
        Log.d("url", image_url);

        Glide.with(ForecastDetailActivity.this)
                .load(image_url)
                .centerCrop()
                .crossFade()
                .into(weatherIcon);

    }
}
