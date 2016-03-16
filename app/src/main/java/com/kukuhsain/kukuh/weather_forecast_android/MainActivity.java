package com.kukuhsain.kukuh.weather_forecast_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    public void inputCity(View view) {
        String city = "London";
        String mode = "json";
        String units = "metric";
        String cnt = "16";
        String appid = "cef209f6f46bb527dba385b81b808ce9";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForecastService service = retrofit.create(ForecastService.class);
        Call<ForecastResult> result = service.result(city, mode, units, cnt, appid);

        result.enqueue(new Callback<ForecastResult>() {
                           @Override
                           public void onResponse(Call<ForecastResult> call, Response<ForecastResult> response) {
                               Log.d("success", call.toString());
                               Log.d("success", response.message());
                               for (int i=0; i<response.body().list.size(); i++) {
                                   Log.d("success", "" + response.body().list.get(i).weather.get(0).icon);
                               }
                           }

                           @Override
                           public void onFailure(Call<ForecastResult> call, Throwable t) {
                               Log.d("user", "Gagalll");
                               Log.e("errorrr", t.getMessage());
                           }
                       }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
