package com.example.m_weather.repos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.m_weather.MainActivity;
import com.example.m_weather.datamodels.WeatherResponse;
import com.example.m_weather.helpers.NoNetworkException;
import com.example.m_weather.helpers.RetrofitApi;
import com.example.m_weather.helpers.WeatherApiListener;
import com.example.m_weather.helpers.WeatherIconService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepos {
    public WeatherApiListener weatherApiListener;
    private static ApiRepos instance;

    private ApiRepos() {
    }

    public static ApiRepos getInstance(){
        if(instance == null){
            synchronized (ApiRepos.class) {
                if (instance == null)
                    instance = new ApiRepos();
            }
        }
        return instance;
    }
    WeatherResponse data= null;
    public void getWeatherData(String cityName,String apiKey) {
        Call<WeatherResponse> call = RetrofitApi.invoke().getDataC(cityName, apiKey);
        String url = String.valueOf(call.request().url());
        Log.d("URL HItt:",url);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if(response.isSuccessful()) {
                    data = response.body();
                    weatherApiListener.OnSuccess(data);
                }
                else{
                    weatherApiListener.onFailure("No City Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                data = null;
                weatherApiListener.onFailure("No City Found");
            }
        });
    }


    public void getImage(String imgCode){
        Call<ResponseBody> call = WeatherIconService.invoke().getFavicon(imgCode+"@2x.png");
        String url = String.valueOf(call.request().url());
        Log.d("URL HItt:",url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    weatherApiListener.SetWeatherIcon(bmp);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
               weatherApiListener.onFailure("Could not Load Image");
            }
        });
    }
}
