package com.example.m_weather;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.m_weather.databinding.DialogSearchBinding;
import com.example.m_weather.datamodels.WeatherResponse;
import com.example.m_weather.helpers.WeatherApiListener;
import com.example.m_weather.repos.ApiRepos;

public class MainViewModel extends ViewModel {
    WeatherApiListener listener;
    String ApiKey="";
    public String cityName="";


    public void FuncGetData(){
        listener.onStarted();
        ApiRepos repos = new ApiRepos();
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
        ApiRepos repos = new ApiRepos();
        repos.weatherApiListener = listener;
        repos.getImage(iconCode);
    }
}
