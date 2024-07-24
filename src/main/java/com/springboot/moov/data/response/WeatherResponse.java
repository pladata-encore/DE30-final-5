package com.springboot.moov.data.response;

import com.springboot.moov.data.entity.WeatherEntity;

import java.util.List;

public class WeatherResponse {
    private WeatherMain main;
    private List<Weather> weather;

    // Getters and Setters
    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
