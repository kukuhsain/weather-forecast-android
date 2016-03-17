package com.kukuhsain.kukuh.weather_forecast_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        final Gson gson = new Gson();
        String stringForecast = getIntent().getStringExtra("response");
        final ForecastResult forecast = gson.fromJson(stringForecast, ForecastResult.class);
        Log.d("success", forecast.city.name);

        ForecastDataList[] forecastDataArray = new ForecastDataList[forecast.list.size()];
        forecast.list.toArray(forecastDataArray);

        ListAdapter listAdapter = new ListCustomAdapter(ForecastListActivity.this, forecastDataArray);
        ListView listView = (ListView) findViewById(R.id.forecast_list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ForecastListActivity.this, ForecastDetailActivity.class);
                        intent.putExtra("city", gson.toJson(forecast.city));
                        intent.putExtra("single-list", gson.toJson(forecast.list.get(position)));
                        startActivity(intent);
                    }
                }
        );

    }
}
