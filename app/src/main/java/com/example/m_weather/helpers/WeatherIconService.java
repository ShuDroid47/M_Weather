package com.example.m_weather.helpers;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WeatherIconService {

    @GET
    Call<ResponseBody> getFavicon(@Url String url);

    static WeatherIconService invoke() {
        WeatherIconService wis = new Retrofit.Builder()
                .baseUrl("https://openweathermap.org/img/wn/")
                .build()
                .create(WeatherIconService.class);
        return wis;
    }
}
