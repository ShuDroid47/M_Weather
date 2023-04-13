package com.example.m_weather.datamodels;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private Double temp;
    @SerializedName("humidity")
    private Integer humidity;
    @SerializedName("pressure")
    private Integer pressure;
    @SerializedName("temp_min")
    private Double tempMin;
    @SerializedName("temp_max")
    private Double tempMax;

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    @SerializedName("feels_like")
    private Double feelsLike;
    public Main() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }
}
