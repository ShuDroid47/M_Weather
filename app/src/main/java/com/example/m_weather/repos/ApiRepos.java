package com.example.m_weather.repos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.m_weather.datamodels.WeatherResponse;
import com.example.m_weather.helpers.RetrofitApi;
import com.example.m_weather.helpers.WeatherApiListener;
import com.example.m_weather.helpers.WeatherIconService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepos {
    public WeatherApiListener weatherApiListener;

    WeatherResponse data= null;
    public void getWeatherData(String cityName,String apiKey) {
        Call<WeatherResponse> call = RetrofitApi.invoke().getDataC(cityName, apiKey);
        String url = String.valueOf(call.request().url());
        Log.d("URL HItt:",url);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful()) {
                    data = response.body();
                    weatherApiListener.OnSuccess(data);
                }
                else{
                    weatherApiListener.onFailure("No City Found");
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
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
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    weatherApiListener.SetWeatherIcon(bmp);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
               weatherApiListener.onFailure("Could not Load Image");
            }
        });
    }
}
