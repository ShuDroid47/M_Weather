package com.example.m_weather.helpers;

import android.annotation.SuppressLint;

public class KelvinConverter {

    public KelvinConverter() {
    }

    public String getCelsius(double val)
    {
        return String.format("%.0f",(val - 273.15));

    }

    public String getFahrenheit(double val){
        return String.format("%.0f",((val - 273.15) * 9 / 5 + 32));
    }
}
