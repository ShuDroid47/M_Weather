package com.example.m_weather.datamodels;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private Double speed;
    @SerializedName("deg")
    private Double deg;

    public Wind() {
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }
}
