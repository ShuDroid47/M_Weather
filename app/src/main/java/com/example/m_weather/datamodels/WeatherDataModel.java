package com.example.m_weather.datamodels;

public class WeatherDataModel {

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    private String temp="";
    private String minTemp="";
    private String maxTemp="";
    private String metric="";

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    private String humidity="";
    private String pressure="";
    private String visibility="";
    private String windspeed="";

    public String getFeellike() {
        return feellike;
    }

    public void setFeellike(String feellike) {
        this.feellike = feellike;
    }

    private String feellike="";

    public String getMaxbymin() {
        return maxbymin;
    }

    public void setMaxbymin(String maxbymin) {
        this.maxbymin = maxbymin;
    }

    private String maxbymin="";


}
