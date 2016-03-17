package com.kukuhsain.kukuh.weather_forecast_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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
        final String mode = "json";
        final String units = "metric";
        final String cnt = "16";
        final String appid = "cef209f6f46bb527dba385b81b808ce9";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ForecastService service = retrofit.create(ForecastService.class);

        // Alert Dialog
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Input City");

        View dialogView = (View) LayoutInflater.from(MainActivity.this).inflate(R.layout.input_city_dialog, null);
        final EditText inputCity = (EditText) dialogView.findViewById(R.id.input_city);

        alertDialog.setView(dialogView);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Call<ForecastResult> result = service.result(inputCity.getText().toString(), mode, units, cnt, appid);
                result.enqueue(new Callback<ForecastResult>() {
                                   @Override
                                   public void onResponse(Call<ForecastResult> call, Response<ForecastResult> response) {
                                       Log.d("success", call.toString());
                                       Log.d("success", response.message());
                                       for (int i = 0; i < response.body().list.size(); i++) {
                                           Log.d("success", "" + response.body().list.get(i).weather.get(0).icon);
                                       }

                                       Gson gson = new Gson();
                                       Intent intent = new Intent(MainActivity.this, ForecastListActivity.class);
                                       intent.putExtra("response", gson.toJson(response.body()));
                                       startActivity(intent);

                                   }

                                   @Override
                                   public void onFailure(Call<ForecastResult> call, Throwable t) {
                                       Log.d("user", "Gagalll");
                                       Log.e("errorrr", t.getMessage());
                                   }
                               }
                );
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Please wait...", Toast.LENGTH_LONG).show();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * get access location
     */
    /*private void accessLocation() {
        // cek apakah sudah memiliki permission untuk access fine location
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // cek apakah perlu menampilkan info kenapa membutuhkan access fine location
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(MainActivity.this, "Access to your location is needed",
                        Toast.LENGTH_SHORT).show();

                String[] perm = { Manifest.permission.ACCESS_FINE_LOCATION };
                ActivityCompat.requestPermissions(MainActivity.this, perm,
                        RP_ACCESS_LOCATION);
            } else {
                // request permission untuk access fine location
                String[] perm = { Manifest.permission.ACCESS_FINE_LOCATION };
                ActivityCompat.requestPermissions(MainActivity.this, perm,
                        RP_ACCESS_LOCATION);
            }
        } else {
            // permission access fine location didapat
            Toast.makeText(WithoutEasyPermissionActivity.this, "Thank you for allowing to access your location",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RP_ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do location thing
                    // access location didapatkan
                    Toast.makeText(WithoutEasyPermissionActivity.this, "Yay, has location permission",
                            Toast.LENGTH_SHORT).show();

                    doSomething();
                } else {
                    // access location ditolak user
                    Toast.makeText(WithoutEasyPermissionActivity.this, "permission ditolak user",
                            Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }*/

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
