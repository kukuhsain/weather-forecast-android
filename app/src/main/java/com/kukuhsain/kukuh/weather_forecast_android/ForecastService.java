package com.kukuhsain.kukuh.weather_forecast_android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kukuh on 16/03/16.
 */
public interface ForecastService {
    @GET("/data/2.5/forecast/daily")
    Call<ForecastResult> callByCity(@Query("q") String city,
                                @Query("mode") String mode,
                                @Query("units") String units,
                                @Query("cnt") String cnt,
                                @Query("appid") String appid);

    @GET("/data/2.5/forecast/daily")
    Call<ForecastResult> callByLatLon(@Query("lat") String latitude,
                                @Query("lon") String longitude,
                                @Query("mode") String mode,
                                @Query("units") String units,
                                @Query("cnt") String cnt,
                                @Query("appid") String appid);
}
