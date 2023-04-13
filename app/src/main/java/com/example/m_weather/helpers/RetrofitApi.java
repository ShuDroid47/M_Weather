package com.example.m_weather.helpers;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface RetrofitApi {


    @NonNull
    static WeatherService invoke() {
        WeatherService rfit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                .create(WeatherService.class);

           return rfit;
    }
}
