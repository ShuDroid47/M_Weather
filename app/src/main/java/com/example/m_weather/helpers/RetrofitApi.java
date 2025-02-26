package com.example.m_weather.helpers;

import android.content.Context;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface RetrofitApi {


    @NonNull
    static WeatherService invoke() {
//        OkHttpClient.Builder httpClinet = new OkHttpClient.Builder();
//        httpClinet.addInterceptor((Interceptor) ConnectivityInterceptor);
        WeatherService rfit = new Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                .create(WeatherService.class);

        return rfit;
    }
}
