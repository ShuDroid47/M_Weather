package com.example.m_weather;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.m_weather.helpers.ShareUtils;
import com.example.m_weather.helpers.WeatherApiListener;
import com.example.m_weather.repos.ApiRepos;

public class MainViewModel extends ViewModel {
    WeatherApiListener listener;
    String ApiKey="";
    public String cityName="";
    private ApiRepos repos;

    public void FuncGetData(){
        listener.onStarted();
        repos = ApiRepos.getInstance();
        repos.weatherApiListener = listener;
        repos.getWeatherData(cityName, ApiKey);
    }

    public void OnSearchClick(View v){
        if(!cityName.isEmpty()) {
            FuncGetData();
        }
        else
            listener.onFailure("Enter City Name");
    }

    public void loadImage(String iconCode) {
        //repos.getInstance();
        repos.weatherApiListener = listener;
        repos.getImage(iconCode);
    }

    public void processShareApplication() {

    }
}
