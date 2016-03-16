package com.kukuhsain.kukuh.weather_forecast_android;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kukuh on 16/03/16.
 */
public class ForecastDataList {

    @SerializedName("dt")
    @Expose
    public Integer dt;
    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("pressure")
    @Expose
    public Float pressure;
    @SerializedName("humidity")
    @Expose
    public Integer humidity;
    @SerializedName("weather")
    @Expose
    public java.util.List<Weather> weather = new ArrayList<Weather>();
    @SerializedName("speed")
    @Expose
    public Float speed;
    @SerializedName("deg")
    @Expose
    public Integer deg;
    @SerializedName("clouds")
    @Expose
    public Integer clouds;
    @SerializedName("rain")
    @Expose
    public Float rain;

}
