package com.kukuhsain.kukuh.weather_forecast_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

public class ForecastListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_list);
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
        String stringForecast = getIntent().getStringExtra("response");
        ForecastResult forecast = gson.fromJson(stringForecast, ForecastResult.class);
        Log.d("success", forecast.city.name);

        ForecastDataList[] forecastDataArray = new ForecastDataList[forecast.list.size()];
        forecast.list.toArray(forecastDataArray);

        ListAdapter listAdapter = new ListCustomAdapter(ForecastListActivity.this, forecastDataArray);
        ListView listView = (ListView) findViewById(R.id.forecast_list);
        listView.setAdapter(listAdapter);

    }
}
