package com.example.m_weather.helpers;

import com.example.m_weather.datamodels.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getDataLtLo(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    //q={city name},{country code}&appid={API key}
    @GET("data/2.5/weather?q=")
    Call<WeatherResponse> getDataCC(@Query("city name") String city_name, @Query("country code") String country_code, @Query("APPID") String app_id);

    //q={city name},{state code},{country code}&appid={API key}
    @GET("data/2.5/weather?q=")
    Call<WeatherResponse> getDataCSC(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String app_id);

    //q={city name}&appid={API key}
    @GET("data/2.5/weather?")
    Call<WeatherResponse> getDataC(@Query("q") String cityname, @Query("appid") String app_id);
}
