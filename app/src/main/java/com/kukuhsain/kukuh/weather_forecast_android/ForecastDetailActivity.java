package com.kukuhsain.kukuh.weather_forecast_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

        Log.d("city", stringCity);
        Log.d("list", stringSingleList);

        TextView cityCountry = (TextView) findViewById(R.id.detail_city_country);
        TextView weatherMain = (TextView) findViewById(R.id.detail_weather_main);
        TextView weatherDesc = (TextView) findViewById(R.id.detail_weather_desc);
        TextView wind = (TextView) findViewById(R.id.detail_wind);
        TextView cloudiness = (TextView) findViewById(R.id.detail_cloudiness);
        TextView pressure = (TextView) findViewById(R.id.detail_pressure);
        TextView humidity = (TextView) findViewById(R.id.detail_humidity);
        TextView rangeTemp = (TextView) findViewById(R.id.detail_range_temp);
        TextView timeTemp = (TextView) findViewById(R.id.detail_time_temp);
        TextView coord = (TextView) findViewById(R.id.detail_coord);

        

    }
}
