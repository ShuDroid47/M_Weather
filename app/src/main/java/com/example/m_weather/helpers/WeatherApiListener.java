package com.example.m_weather.helpers;

import android.graphics.Bitmap;

import com.example.m_weather.datamodels.WeatherResponse;

public interface WeatherApiListener {
    void onStarted();
    void OnSuccess(WeatherResponse weatherData);
    void SetWeatherIcon(Bitmap map);
    void onEnd();
    void onFailure(String errMsge);
}
